package model;

import java.util.HashMap;
import java.util.Map;

public class UserStorage {

    private Map<String, User> users = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public User getUserByUsername(String username) {
        return users.get(username);
    }
}
