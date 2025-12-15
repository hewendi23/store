package mailserver.auth;

import mailserver.db.SqlServerUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    public boolean authenticate(String username, String password) {
        String u = normalizeUsername(username);
        try (Connection conn = SqlServerUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT password FROM users WHERE username = ?")) {
            ps.setString(1, u);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String stored = rs.getString(1);
                    return stored != null && stored.equals(password);
                }
            }
        } catch (SQLException e) {
            System.out.println("AUTH ERROR " + e.getMessage());
        }
        return false;
    }

    public boolean userExists(String username) {
        String u = normalizeUsername(username);
        try (Connection conn = SqlServerUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT 1 FROM users WHERE username = ?")) {
            ps.setString(1, u);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println("USER CHECK ERROR " + e.getMessage());
        }
        return false;
    }

    private String normalizeUsername(String username) {
        if (username == null) return null;
        int at = username.indexOf('@');
        if (at > 0) {
            return username.substring(0, at);
        }
        return username;
    }
}
