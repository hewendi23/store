package mailserver.pop3;

import mailserver.storage.MailStorageService;

import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Pop3Server implements Runnable {
    private final int port;
    private final ExecutorService pool = Executors.newCachedThreadPool();
    private final MailStorageService storage = new MailStorageService(Path.of("maildata"));

    public Pop3Server(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("POP3 LISTEN " + port);
            while (true) {
                Socket client = server.accept();
                client.setSoTimeout(120000);
                pool.submit(new Pop3Session(client, storage));
            }
        } catch (Exception e) {
            System.out.println("POP3 ERROR " + e.getMessage());
        }
    }
}

