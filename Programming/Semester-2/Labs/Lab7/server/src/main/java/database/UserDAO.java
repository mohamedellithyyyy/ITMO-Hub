package database;

import network.UserCredentials;
import security.PasswordHasher;

import java.sql.*;
import java.util.logging.Logger;

public class UserDAO {
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());

    public boolean register(String username, String password) {
        String sql = "INSERT INTO users (username, password_hash) VALUES (?, ?)";
        try (PreparedStatement stmt = DatabaseManager.getInstance().getConnection().prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, PasswordHasher.hashPassword(password));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            if (e.getSQLState() != null && e.getSQLState().equals("23505")) {
                return false; // unique violation
            }
            LOGGER.severe("Registration error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public UserCredentials authenticate(String username, String password) {
        String sql = "SELECT id, password_hash FROM users WHERE username = ?";
        try (PreparedStatement stmt = DatabaseManager.getInstance().getConnection().prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedHash = rs.getString("password_hash");
                if (PasswordHasher.verifyPassword(password, storedHash)) {
                    UserCredentials creds = new UserCredentials(username, password);
                    creds.setAuthenticated(true);
                    creds.setUserId(rs.getInt("id"));
                    return creds;
                }
            }
            return null;
        } catch (SQLException e) {
            LOGGER.severe("Authentication error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}