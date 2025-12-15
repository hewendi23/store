package mailserver.pop3;

import mailserver.auth.UserService;
import mailserver.storage.MailStorageService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Pop3Session implements Runnable {
    private final Socket socket;
    private final MailStorageService storage;
    private final UserService userService = new UserService();
    private String authedUser;

    public Pop3Session(Socket socket, MailStorageService storage) {
        this.socket = socket;
        this.storage = storage;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8))) {
            write(out, "+OK POP3 ready");
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("POP3 RECV " + line);
                String upper = line.toUpperCase();
                if (upper.startsWith("USER ")) {
                    String u = line.substring(5).trim();
                    int at = u.indexOf('@');
                    if (at > 0) {
                        authedUser = u.substring(0, at);
                    } else {
                        authedUser = u;
                    }
                    write(out, "+OK");
                } else if (upper.startsWith("PASS ")) {
                    String p = line.substring(5).trim();
                    if (authedUser != null && userService.authenticate(authedUser, p)) {
                        write(out, "+OK Authenticated");
                    } else {
                        write(out, "-ERR Authentication failed");
                        authedUser = null;
                    }
                } else if (upper.equals("STAT")) {
                    if (!ensureAuth(out)) continue;
                    List<MailStorageService.MailMeta> mails = storage.listMails(authedUser);
                    long total = mails.stream().mapToLong(m -> m.size).sum();
                    write(out, "+OK " + mails.size() + " " + total);
                } else if (upper.startsWith("LIST")) {
                    if (!ensureAuth(out)) continue;
                    List<MailStorageService.MailMeta> mails = storage.listMails(authedUser);
                    if (upper.equals("LIST")) {
                        write(out, "+OK " + mails.size());
                        for (MailStorageService.MailMeta m : mails) {
                            write(out, m.id + " " + m.size);
                        }
                        write(out, ".");
                    } else {
                        String[] parts = line.split("\\s+");
                        int id = Integer.parseInt(parts[1]);
                        MailStorageService.MailMeta meta = mails.stream().filter(x -> x.id == id).findFirst().orElse(null);
                        if (meta == null) {
                            write(out, "-ERR no such message");
                        } else {
                            write(out, "+OK " + meta.id + " " + meta.size);
                        }
                    }
                } else if (upper.startsWith("RETR")) {
                    if (!ensureAuth(out)) continue;
                    String[] parts = line.split("\\s+");
                    int id = Integer.parseInt(parts[1]);
                    String path = storage.getMailContentPath(id, authedUser);
                    if (path == null) {
                        write(out, "-ERR no such message");
                    } else {
                        File f = new File(path);
                        long size = f.exists() ? f.length() : 0;
                        write(out, "+OK " + size + " octets");
                        try (FileInputStream fis = new FileInputStream(f)) {
                            byte[] buf = new byte[8192];
                            int n;
                            while ((n = fis.read(buf)) > 0) {
                                String s = new String(buf, 0, n, StandardCharsets.UTF_8);
                                out.write(s);
                            }
                        }
                        out.write("\r\n.\r\n");
                        out.flush();
                        System.out.println("POP3 SEND RETR " + id);
                    }
                } else if (upper.startsWith("DELE")) {
                    if (!ensureAuth(out)) continue;
                    int id = Integer.parseInt(line.split("\\s+")[1]);
                    storage.deleteMail(id, authedUser);
                    write(out, "+OK message deleted");
                } else if (upper.equals("QUIT")) {
                    write(out, "+OK Bye");
                    break;
                } else {
                    write(out, "-ERR");
                }
            }
        } catch (Exception e) {
            System.out.println("POP3 SESSION ERROR " + e.getMessage());
        }
        try { socket.close(); } catch (Exception ignored) {}
    }

    private boolean ensureAuth(BufferedWriter out) throws Exception {
        if (authedUser == null) {
            write(out, "-ERR not authorized");
            return false;
        }
        return true;
    }

    private void write(BufferedWriter out, String s) throws Exception {
        System.out.println("POP3 SEND " + s);
        out.write(s);
        out.write("\r\n");
        out.flush();
    }
}
