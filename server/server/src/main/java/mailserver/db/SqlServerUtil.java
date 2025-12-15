package mailserver.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlServerUtil {
    private static String url = System.getenv().getOrDefault("DB_URL", "jdbc:sqlserver://localhost:1433;databaseName=emailsystem;encrypt=false;trustServerCertificate=true");
    private static String user = System.getenv().getOrDefault("DB_USER", "sa");
    private static String password = System.getenv().getOrDefault("DB_PASSWORD", "Hrandytaylor69@");

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("SQLSERVER JDBC DRIVER NOT FOUND " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void initSchema() throws SQLException {
        try (Connection conn = getConnection(); Statement st = conn.createStatement()) {
            st.executeUpdate("""
                IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='users' and xtype='U')
                CREATE TABLE users (
                  id INT IDENTITY PRIMARY KEY,
                  username NVARCHAR(255) UNIQUE NOT NULL,
                  password NVARCHAR(255) NOT NULL
                )
            """);
            st.executeUpdate("""
                IF NOT EXISTS (SELECT 1 FROM users WHERE username='testuser')
                INSERT INTO users(username, password) VALUES('testuser', '123456')
            """);
            st.executeUpdate("""
                IF NOT EXISTS (SELECT 1 FROM users WHERE username='testuser2')
                INSERT INTO users(username, password) VALUES('testuser2', '123456')
            """);
            st.executeUpdate("""
                IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='mails' and xtype='U')
                CREATE TABLE mails (
                  id INT IDENTITY PRIMARY KEY,
                  sender NVARCHAR(512) NOT NULL,
                  receiver NVARCHAR(512) NOT NULL,
                  subject NVARCHAR(512),
                  content_path NVARCHAR(1024) NOT NULL,
                  created_time DATETIME2 NOT NULL DEFAULT SYSDATETIME(),
                  is_deleted BIT NOT NULL DEFAULT 0
                )
            """);
            st.executeUpdate("""
                IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='attachments' and xtype='U')
                CREATE TABLE attachments (
                  id INT IDENTITY PRIMARY KEY,
                  mail_id INT NOT NULL,
                  filename NVARCHAR(512),
                  file_path NVARCHAR(1024),
                  mime_type NVARCHAR(255),
                  FOREIGN KEY (mail_id) REFERENCES mails(id)
                )
            """);
        }
    }
}
