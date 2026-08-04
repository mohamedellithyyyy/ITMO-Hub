package auth;

import network.UserCredentials;

public class LoginManager {
    private UserCredentials currentUser;

    public boolean isLoggedIn() {
        return currentUser != null && currentUser.isAuthenticated();
    }

    public void setCredentials(UserCredentials credentials) {
        this.currentUser = credentials;
    }

    public UserCredentials getCredentials() {
        return currentUser;
    }

    public void logout() {
        currentUser = null;
    }
}
