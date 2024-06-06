package com.infosys.dao;

import java.util.ArrayList;
import com.infosys.pojo.User;

public class UserDAO {
    ArrayList<User> users;
    private int nextUserId;

    public UserDAO() {
        this.users = new ArrayList<>();
        this.nextUserId = 1;
    }

    public ArrayList<User> getAllUsers() {
        if (users.isEmpty()) {
            return null;
        } else {
            return users;
        }
    }


    // add a user to the database
    public void addUser(User user) {
        user.setUserId(nextUserId++);
        users.add(user);
    }

    // retrieve a user by ID
    public User getUserById(int id) {
        for (User user : users) {
            if (user.getUserId() == id) {
                return user;
            }
        }
        // User not found
        return null;
    }

    // retrieve a user by username
    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        // User not found
        return null;
    }

    // update user information
    public void updateUser(User user) {
        for (User u : users) {
            if (u.getUserId() == user.getUserId()) {

                u.setUsername(user.getUsername());
                u.setPassword(user.getPassword());
                u.setRole(user.getRole());
                break;
            }
        }
    }

    // delete a user
    public void deleteUser(int userId) {
        users.removeIf(user -> user.getUserId() == userId);
    }




}
