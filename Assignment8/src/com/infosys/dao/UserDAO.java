package com.infosys.dao;

import com.infosys.exception.*;
import com.infosys.pojo.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDAO {
    private final List<User> users = Collections.synchronizedList(new ArrayList<>());
    private int nextUserId = 1;

    public UserDAO() {
    }

    public synchronized ArrayList<User> getAllUsers() throws UserNotFoundException {
        if (users.isEmpty()) {
            throw new UserNotFoundException("No users found.");
        } else {
            // Return a copy to avoid modification by reference
            return new ArrayList<>(users);
        }
    }

    public synchronized void addUser(User user) throws DuplicateUserException {
        for (User existingUser : users) {
            if (existingUser.getUsername().equals(user.getUsername())) {
                throw new DuplicateUserException("User with username " + user.getUsername() + " already exists.");
            }
        }
        user.setUserId(nextUserId++);
        users.add(user);
    }

    public synchronized User getUserById(int id) throws UserNotFoundException {
        for (User user : users) {
            if (user.getUserId() == id) {
                return user;
            }
        }
        throw new UserNotFoundException("User with ID " + id + " not found.");
    }

    public synchronized User getUserByUsername(String username) throws UserNotFoundException {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        throw new UserNotFoundException("User with username " + username + " not found.");
    }

    public synchronized void updateUser(User user) throws UserNotFoundException {
        boolean userFound = false;
        for (User u : users) {
            if (u.getUserId() == user.getUserId()) {
                u.setUsername(user.getUsername());
                u.setPassword(user.getPassword());
                u.setRole(user.getRole());
                userFound = true;
                break;
            }
        }
        if (!userFound) {
            throw new UserNotFoundException("User with ID " + user.getUserId() + " not found.");
        }
    }

    public synchronized void deleteUser(int userId) throws UserNotFoundException {
        boolean userRemoved = users.removeIf(user -> user.getUserId() == userId);
        if (!userRemoved) {
            throw new UserNotFoundException("User with ID " + userId + " not found.");
        }
    }
}
