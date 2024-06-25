package com.infosys.controller;

import com.infosys.service.TaskService;
import com.infosys.service.UserService;

import java.util.Scanner;
import java.util.InputMismatchException;
import com.infosys.pojo.User;

public class Controller {

    private Scanner scanner = new Scanner(System.in);
    private TaskService taskService = new TaskService();
    private UserService userService = new UserService(taskService);

    public static void main(String[] args) {
        new Controller().start();
    }


    // Method to start the application
    public void start() {
        while (true) {
            // Display main menu
            System.out.println("Welcome to Todo Manager!");
            System.out.println("0. Exit");
            System.out.println("1. Register");
            System.out.println("2. Login");

            // Read user choice
            int choice = 0;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            // Process user choice
            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 0:
                    System.out.println("Exited!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method for user registration
    private void register() {
        System.out.println("Registration");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            // Call UserService to register user
            userService.registerUser(username, password);
            System.out.println("Registration successful!");
        } catch (Exception e) {
            System.out.println("Error during registration: " + e.getMessage());
        }
    }

    // Method for user login
    private void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            // Call UserService to login user
            if (userService.loginUser(username, password)) {
                User user = userService.getUserDetails(username);
                startUserSession(user);
            }
        } catch (Exception e) {
            System.out.println("Error during login: " + e.getMessage());
        }
    }


    // Method to start a new thread for the logged-in user
    private void startUserSession(User user) {
        UserInteractionHandler userInteractionHandler = new UserInteractionHandler(userService, taskService, user);
        Thread userThread = new Thread(userInteractionHandler);
        userThread.start();
    }
    
}