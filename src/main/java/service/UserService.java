package service;

import model.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {

    private final Map<String, User> users = new HashMap<>();

    public User createUser(User user) {
        users.put(user.getUsername(), user);
        return user;
    }

    public User getUserByUsername(String username) {
        return users.get(username);
    }

    public void addUser(User newUser) {
    }
}
