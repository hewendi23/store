package mailserver.smtp;

import mailserver.auth.UserService;
import mailserver.storage.MailStorageService;

import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SmtpServer implements Runnable {
    private final int port;
    private final ExecutorService pool = Executors.newCachedThreadPool();
    private final UserService userService = new UserService();
    private final MailStorageService storage = new MailStorageService(Path.of("maildata"));

    public SmtpServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("SMTP LISTEN " + port);
            while (true) {
                Socket client = server.accept();
                client.setSoTimeout(120000);
                pool.submit(new SmtpSession(client, userService, storage));
            }
        } catch (Exception e) {
            System.out.println("SMTP ERROR " + e.getMessage());
        }
    }
}

