import java.io.*;
import java.util.*;

public abstract class User { //
    private String username;
    private String password;

    public User(String user, String pass) {
        this.username = user;
        this.password = pass;
    }

    public void setUsername(String newUser) {
        this.username = newUser;
    }
    public String getUsername() {
        return this.username;
    }
    public void setPassword(String newPass) {
        this.password = newPass;
    }
    public String getPassword() {
        return this.password;
    }


    public abstract boolean validateLogin(String userAttempt, String passwordAttempt);

    @Override
    public abstract String toString();
}