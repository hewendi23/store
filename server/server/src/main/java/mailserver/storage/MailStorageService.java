package mailserver.storage;

import mailserver.db.SqlServerUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MailStorageService {
    private final Path baseDir;

    public MailStorageService(Path baseDir) {
        this.baseDir = baseDir;
    }

    public int saveMail(String sender, List<String> receivers, String rawData) throws Exception {
        String subject = extractSubject(rawData);
        int lastMailId = -1;
        for (String receiver : receivers) {
            String user = normalizeUsername(receiver);
            Path userInbox = baseDir.resolve(user).resolve("inbox");
            Files.createDirectories(userInbox);
            int newId = nextMailId();
            String fileName = "mail_" + newId + ".eml";
            Path emlPath = userInbox.resolve(fileName);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(emlPath.toFile(), StandardCharsets.UTF_8))) {
                bw.write(rawData);
            }
            try (Connection conn = SqlServerUtil.getConnection();
                 PreparedStatement ps = conn.prepareStatement("INSERT INTO mails (sender, receiver, subject, content_path, created_time, is_deleted) VALUES (?, ?, ?, ?, SYSDATETIME(), 0)", PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, sender);
                ps.setString(2, user);
                ps.setString(3, subject);
                ps.setString(4, emlPath.toString());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        lastMailId = rs.getInt(1);
                    }
                }
            }
        }
        return lastMailId;
    }

    public List<MailMeta> listMails(String username) throws SQLException {
        List<MailMeta> list = new ArrayList<>();
        try (Connection conn = SqlServerUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT id, content_path FROM mails WHERE receiver = ? AND is_deleted = 0 ORDER BY id")) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String path = rs.getString(2);
                    File f = new File(path);
                    long size = f.exists() ? f.length() : 0;
                    list.add(new MailMeta(id, path, size));
                }
            }
        }
        return list;
    }

    public String getMailContentPath(int mailId, String username) throws SQLException {
        try (Connection conn = SqlServerUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT content_path FROM mails WHERE id = ? AND receiver = ?")) {
            ps.setInt(1, mailId);
            ps.setString(2, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
        }
        return null;
    }

    public void deleteMail(int mailId, String username) throws SQLException {
        try (Connection conn = SqlServerUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE mails SET is_deleted = 1 WHERE id = ? AND receiver = ?")) {
            ps.setInt(1, mailId);
            ps.setString(2, username);
            ps.executeUpdate();
        }
    }

    private int nextMailId() throws SQLException {
        try (Connection conn = SqlServerUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT ISNULL(MAX(id),0) + 1 FROM mails")) {
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 1;
    }

    private String extractSubject(String raw) {
        String[] lines = raw.split("\\r?\\n");
        for (String line : lines) {
            if (line.toUpperCase().startsWith("SUBJECT:")) {
                return line.substring(8).trim();
            }
            if (line.isEmpty()) break;
        }
        return null;
    }

    private String normalizeUsername(String value) {
        if (value == null) return null;
        int at = value.indexOf('@');
        if (at > 0) {
            return value.substring(0, at);
        }
        return value;
    }

    public static class MailMeta {
        public final int id;
        public final String path;
        public final long size;
        public MailMeta(int id, String path, long size) {
            this.id = id;
            this.path = path;
            this.size = size;
        }
    }
}
