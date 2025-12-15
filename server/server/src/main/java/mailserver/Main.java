package mailserver;

import mailserver.db.SqlServerUtil;
import mailserver.pop3.Pop3Server;
import mailserver.smtp.SmtpServer;

public class Main {
    public static void main(String[] args) throws Exception {
        SqlServerUtil.initSchema();
        int smtpPort = Integer.parseInt(System.getenv().getOrDefault("SMTP_PORT", "25"));
        int pop3Port = Integer.parseInt(System.getenv().getOrDefault("POP3_PORT", "110"));
        Thread smtp = new Thread(new SmtpServer(smtpPort));
        Thread pop3 = new Thread(new Pop3Server(pop3Port));
        smtp.setName("SMTP-Server");
        pop3.setName("POP3-Server");
        smtp.start();
        pop3.start();
        smtp.join();
        pop3.join();
    }
}

