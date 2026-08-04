package network;

import java.io.Serializable;

public class UserCredentials implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private boolean authenticated;
    private Integer userId;

    public UserCredentials(String username, String password) {
        this.username = username;
        this.password = password;
        this.authenticated = false;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public boolean isAuthenticated() { return authenticated; }
    public void setAuthenticated(boolean authenticated) { this.authenticated = authenticated; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
}
