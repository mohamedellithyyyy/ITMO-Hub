package security;

import database.UserDAO;
import network.UserCredentials;

public class AuthManager {
    private final UserDAO userDAO = new UserDAO();

    public UserCredentials login(String username, String password) {
        return userDAO.authenticate(username, password);
    }

    public boolean register(String username, String password) {
        return userDAO.register(username, password);
    }
}
