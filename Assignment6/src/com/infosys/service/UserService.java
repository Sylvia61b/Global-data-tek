package com.infosys.service;

import com.infosys.dao.UserDAO;
import com.infosys.pojo.Book;
import com.infosys.pojo.User;

import java.util.Arrays;

public class UserService {
    private UserDAO userDAO;

    // Constructor
    public UserService() {
        this.userDAO = new UserDAO();
    }

    // Register a new user
    public void registerUser(String username, Long userId, String emailId, String password) {
        User newUser = new User(username, userId, emailId, password);
        userDAO.addUser(newUser);
    }

    // Login a user
    public boolean loginUser(String username, String password) {
        return userDAO.verifyUser(username, password);
    }

    // Get user details by username
    public User getUserDetails(String username) {
        return userDAO.getUserByUsername(username);
    }

    // Get user by user ID
    public User getUserById(Long userId) {
        return userDAO.getUserById(userId);
    }

    // Update user information
    public void updateUser(User user) {
        String newUserName = user.getUserName();
        String newEmailId = user.getEmailId();

        // Check if the new username is already taken
        if (userDAO.isUserNameTaken(newUserName)) {
            System.out.println("Username already exists. Please choose a different username.");
        }

        // Update user information
        user.setUserName(newUserName);
        user.setEmailId(newEmailId);

        // Call the updateUser method in UserDAO to persist changes
        userDAO.updateUser(user);

        System.out.println("User information updated successfully.");
    }

    // Delete a user by username
    public void deleteUser(String username) {
        userDAO.deleteUser(username);
    }

    // View all books of a user
    public void viewAllBooks(User user, BookService bookService) {
        System.out.println("New Books: ");
        bookService.printBooks(user.getNewBooks());

        System.out.println("Favourite Books: ");
        bookService.printBooks(user.getFavourite());

        System.out.println("Completed Books: ");
        bookService.printBooks(user.getCompleted());
    }

    // Mark a book as completed for a user
    public boolean markBookAsCompleted(User user, Long bookId) {
        for (int i = 0; i < user.getNewBooks().length; i++) {
            Book book = user.getNewBooks()[i];
            if (book != null && book.getBookId().equals(bookId)) {
                // Remove from newBooks
                user.getNewBooks()[i] = null;

                Book[] completedBooks = user.getCompleted();
                for (int j = 0; j < completedBooks.length; j++) {
                    if (completedBooks[j] == null) {
                        // Add to completed
                        completedBooks[j] = book;
                        return true;
                    }
                }

                // If completed array is full, resize it
                completedBooks = Arrays.copyOf(completedBooks, completedBooks.length * 2);
                completedBooks[user.getCompleted().length] = book;
                user.setCompleted(completedBooks);
                return true;
            }
        }
        return false;
    }

    // Helper method to check if the user's new books array is full
    private boolean isUserNewBooksFull(User user) {
        for (Book book : user.getNewBooks()) {
            if (book == null) {
                // There is space in the newBooks array
                return false;
            }
        }
        // No space in the newBooks array
        return true;
    }
}
