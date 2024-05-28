package service;

import model.User;

import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private Map<String, User> users;

    public AuthService() {
        users = new HashMap<>();
        // Adding a default student user for testing
        users.put("student", new User("student", "student123", false));
    }

    public User authenticate(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}

