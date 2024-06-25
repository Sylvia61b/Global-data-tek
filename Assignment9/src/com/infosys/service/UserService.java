package com.infosys.service;

import com.infosys.dao.UserDAO;
import com.infosys.pojo.Book;
import com.infosys.pojo.User;

import java.util.List;

public class UserService {
    private UserDAO userDAO;

    // Constructor
    public UserService() {
        this.userDAO = new UserDAO();
    }

    // Register a new user
    public synchronized void registerUser(String username, Long userId, String emailId, String password) {
        User newUser = new User(username, userId, emailId, password);
        userDAO.addUser(newUser);
    }

    // Login a user
    public synchronized boolean loginUser(String username, String password) {
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
    public synchronized void updateUser(User user) {
        String newUserName = user.getUserName();
        String newEmailId = user.getEmailId();

        // Check if the new username is already taken
        if (userDAO.isUserNameTaken(newUserName)) {
            System.out.println("Username already exists. Please choose a different username.");
            return;
        }

        // Update user information
        user.setUserName(newUserName);
        user.setEmailId(newEmailId);

        // Call the updateUser method in UserDAO to persist changes
        userDAO.updateUser(user);

        System.out.println("User information updated successfully.");
    }

    // Delete a user by username
    public synchronized void deleteUser(String username) {
        userDAO.deleteUser(username);
    }

    // View all books of a user
    public synchronized void viewAllBooks(User user, BookService bookService) {
        System.out.println("New Books: ");
        bookService.printBooks(user.getNewBooks());

        System.out.println("Favourite Books: ");
        bookService.printBooks(user.getFavourite());

        System.out.println("Completed Books: ");
        bookService.printBooks(user.getCompleted());
    }

    // Mark a book as completed for a user
    public synchronized boolean markBookAsCompleted(User user, Long bookId) {
        List<Book> newBooks = user.getNewBooks();
        List<Book> completedBooks = user.getCompleted();

        for (int i = 0; i < newBooks.size(); i++) {
            Book book = newBooks.get(i);
            if (book != null && book.getBookId().equals(bookId)) {
                // Remove from newBooks
                newBooks.remove(i);

                // Add to completedBooks
                completedBooks.add(book);
                return true;
            }
        }
        return false;
    }

    // Helper method to check if the user's new books list is full
    private boolean isUserNewBooksFull(User user) {
        return user.getNewBooks().size() >= 10;
    }
}
