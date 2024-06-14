package com.infosys.dao;

import java.util.ArrayList;

import com.infosys.exception.*;
import com.infosys.pojo.User;

public class UserDAO {
    ArrayList<User> users;
    private int nextUserId;

    public UserDAO() {
        this.users = new ArrayList<>();
        this.nextUserId = 1;
    }

    public ArrayList<User> getAllUsers() throws UserNotFoundException {
        if (users.isEmpty()) {
            throw new UserNotFoundException("No users found.");
        } else {
            return users;
        }
    }


    // add a user to the database
    public void addUser(User user) throws DuplicateUserException {
        for (User existingUser : users) {
            if (existingUser.getUsername().equals(user.getUsername())) {
                throw new DuplicateUserException("User with username " + user.getUsername() + " already exists.");
            }
        }
        user.setUserId(nextUserId++);
        users.add(user);
    }

    // retrieve a user by ID
    public User getUserById(int id) throws UserNotFoundException{
        for (User user : users) {
            if (user.getUserId() == id) {
                return user;
            }
        }
        // User not found
        throw new UserNotFoundException("User with ID " + id + " not found.");
    }

    // retrieve a user by username
    public User getUserByUsername(String username) throws UserNotFoundException{
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        // User not found
        throw new UserNotFoundException("User with username " + username + " not found.");
    }

    // update user information
    public void updateUser(User user) throws UserNotFoundException {
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

    // delete a user
    public void deleteUser(int userId) throws UserNotFoundException {
        boolean userRemoved =users.removeIf(user -> user.getUserId() == userId);
        if (!userRemoved) {
            throw new UserNotFoundException("User with ID " + userId + " not found.");
        }
    }

}
