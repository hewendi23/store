package mailserver.smtp;

import mailserver.auth.UserService;
import mailserver.storage.MailStorageService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class SmtpSession implements Runnable {
    private final Socket socket;
    private final UserService userService;
    private final MailStorageService storage;
    private String heloName;
    private String authedUser;
    private String mailFrom;
    private final List<String> rcptTo = new ArrayList<>();
    private boolean inData = false;
    private final StringBuilder dataBuf = new StringBuilder();

    public SmtpSession(Socket socket, UserService userService, MailStorageService storage) {
        this.socket = socket;
        this.userService = userService;
        this.storage = storage;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8))) {
            write(out, "220 localhost SMTP");
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("SMTP RECV " + line);
                if (inData) {
                    if (line.equals(".")) {
                        inData = false;
                        handleDataComplete(out);
                        resetTransaction();
                        continue;
                    } else {
                        if (line.startsWith("..")) line = line.substring(1);
                        dataBuf.append(line).append("\r\n");
                        continue;
                    }
                }
                String upper = line.toUpperCase();
                if (upper.startsWith("EHLO") || upper.startsWith("HELO")) {
                    heloName = line.split("\\s+", 2).length > 1 ? line.split("\\s+", 2)[1] : "";
                    write(out, "250-localhost");
                    write(out, "250-AUTH LOGIN");
                    write(out, "250 OK");
                } else if (upper.startsWith("AUTH LOGIN")) {
                    write(out, "334 " + Base64.getEncoder().encodeToString("Username:".getBytes(StandardCharsets.UTF_8)));
                    String u = in.readLine();
                    String username = new String(Base64.getDecoder().decode(u), StandardCharsets.UTF_8);
                    write(out, "334 " + Base64.getEncoder().encodeToString("Password:".getBytes(StandardCharsets.UTF_8)));
                    String p = in.readLine();
                    String password = new String(Base64.getDecoder().decode(p), StandardCharsets.UTF_8);
                    if (userService.authenticate(username, password)) {
                        authedUser = username;
                        write(out, "235 Authentication successful");
                    } else {
                        write(out, "535 Authentication failed");
                    }
                } else if (upper.startsWith("MAIL FROM:")) {
                    if (authedUser == null) {
                        write(out, "530 Authentication required");
                    } else {
                        mailFrom = extractAddress(line.substring(10).trim());
                        write(out, "250 OK");
                    }
                } else if (upper.startsWith("RCPT TO:")) {
                    if (authedUser == null) {
                        write(out, "530 Authentication required");
                    } else {
                        String rcpt = extractAddress(line.substring(8).trim());
                        rcptTo.add(rcpt);
                        write(out, "250 OK");
                    }
                } else if (upper.equals("DATA")) {
                    if (rcptTo.isEmpty() || mailFrom == null) {
                        write(out, "503 Bad sequence of commands");
                    } else {
                        inData = true;
                        dataBuf.setLength(0);
                        write(out, "354 End data with <CR><LF>.<CR><LF>");
                    }
                } else if (upper.equals("QUIT")) {
                    write(out, "221 Bye");
                    break;
                } else {
                    write(out, "502 Command not implemented");
                }
            }
        } catch (Exception e) {
            System.out.println("SMTP SESSION ERROR " + e.getMessage());
        }
        try {
            socket.close();
        } catch (Exception ignored) {}
    }

    private void handleDataComplete(BufferedWriter out) throws Exception {
        int id = storage.saveMail(mailFrom, rcptTo, dataBuf.toString());
        write(out, "250 OK " + id);
    }

    private void resetTransaction() {
        mailFrom = null;
        rcptTo.clear();
        dataBuf.setLength(0);
    }

    private void write(BufferedWriter out, String s) throws Exception {
        System.out.println("SMTP SEND " + s);
        out.write(s);
        out.write("\r\n");
        out.flush();
    }

    private String extractAddress(String s) {
        int lt = s.indexOf('<');
        int gt = s.indexOf('>');
        if (lt >= 0 && gt > lt) return s.substring(lt + 1, gt);
        return s.replaceAll("^<|>$", "");
    }
}

