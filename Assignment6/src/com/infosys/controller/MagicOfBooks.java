package com.infosys.controller;

import com.infosys.pojo.User;
import com.infosys.service.BookService;
import com.infosys.service.UserService;

import java.util.Scanner;

public class MagicOfBooks {
    private UserService userService;
    private BookService bookService;
    private Scanner scanner;

    // Constructor
    public MagicOfBooks(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
        this.scanner = new Scanner(System.in);
    }

    // Method to start the application
    public void start() {
        while (true) {
            // Display main menu
            System.out.println("Welcome to Magic of Books!");
            System.out.println("0. Exit");
            System.out.println("1. Register");
            System.out.println("2. Login");

            // Read user choice
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            // Process user choice
            if (choice == 1) {
                register();
            } else if (choice == 2) {
                login();
            } else if (choice == 0) {
                System.out.println("Exited!");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method for user registration
    private void register() {
        System.out.println("Registration");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter user ID: ");
        Long userId = scanner.nextLong();
        scanner.nextLine(); // consume newline
        System.out.print("Enter email ID: ");
        String emailId = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Call UserService to register user
        userService.registerUser(username, userId, emailId, password);
        System.out.println("Registration successful!");
    }

    // Method for user login
    private void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Call UserService to login user
        if (userService.loginUser(username, password)) {
            User user = userService.getUserDetails(username);
            userMenu(user);
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    // Method for user-specific menu
    private void userMenu(User user) {
        while (true) {
            // Display user menu
            System.out.println("Welcome " + user.getUserName() + "!");
            System.out.println("1. View all books");
            System.out.println("2. Select a book by ID");
            System.out.println("3. Logout");

            // Read user choice
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            // Process user choice
            if (choice == 1) {
                userService.viewAllBooks(user, bookService);
            } else if (choice == 2) {
                bookService.selectBookById(user);
            } else if (choice == 3) {
                System.out.println("Logging out...");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Main method to start the application
    public static void main(String[] args) {
        // Initialize services
        UserService userService = new UserService();
        BookService bookService = new BookService();

        // Initialize MagicOfBooks controller
        MagicOfBooks magicOfBooks = new MagicOfBooks(userService, bookService);

        // Start the application
        magicOfBooks.start();
    }
}

