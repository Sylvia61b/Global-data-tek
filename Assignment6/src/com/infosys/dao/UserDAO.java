package com.infosys.dao;

import com.infosys.pojo.User;

import java.util.HashMap;
import java.util.Map;

public class UserDAO {
    private Map<String, User> users = new HashMap<>();
    private Map<Long, User> userIds = new HashMap<>();

    // Add a user
    public void addUser(User user) {
        users.put(user.getUserName(), user);
    }

    // Get a user by username
    public User getUserByUsername(String username) {
        return users.get(username);
    }

    // Verify a user's credentials
    public boolean verifyUser(String username, String password) {
        User user = users.get(username);
        return user != null && user.getPassword().equals(password);
    }

    // Retrieve a user by ID
    public User getUserById(Long id) {
        return userIds.get(id);
    }

    //Update user
    public void updateUser(User user) {
        if (users.containsKey(user.getUserName())) {
            users.put(user.getUserName(), user);
            userIds.put(user.getUserId(), user);
        } else {
            System.out.println("User not found.");
        }
    }

    //Delete user
    public void deleteUser(String username) {
        User user = users.remove(username);
        if (user != null) {
            userIds.remove(user.getUserId());
        } else {
            System.out.println("User not found.");
        }
    }

    //Check user exists or not
    public boolean isUserNameTaken(String newUserName) {
        return users.containsKey(newUserName);
    }
}
