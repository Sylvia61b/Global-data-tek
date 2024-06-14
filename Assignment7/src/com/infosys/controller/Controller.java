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
                userMenu(user);
            }
        } catch (Exception e) {
            System.out.println("Error during login: " + e.getMessage());
        }
    }


    // Method for user-specific menu
    private void userMenu(User user) {
        while (true) {
            // Display user menu
            System.out.println("Welcome " + user.getUsername() + "!");
            System.out.println("\nTodo Manager Menu:");
            System.out.println("0. Exit");
            System.out.println("1. Show Tasks");
            System.out.println("2. Add Task");
            System.out.println("3. Delete Task by Name");
            System.out.println("4. Delete Task by ID");
            System.out.println("5. Search Task");
            System.out.println("6. Update A Task");
            System.out.println("7. Assign Task to a user");
            System.out.println("8. Show Assigned Tasks");
            System.out.println("9. Show Tasks In Increasing Order");
            System.out.println("10. Show Tasks In Decreasing Order");
            System.out.println("11. Mark the Task As Completed");
            System.out.println("12. Show Completed Tasks");
            System.out.println("13. Show Incomplete Tasks");
            System.out.print("Choose an option: ");

            int choice = 0;

            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
                continue;
            }


            try{
                switch (choice) {
                    case 0:
                        System.out.println("Exiting Todo Manager.");
                        return;
                    case 1:
                        userService.showTasks();
                        break;
                    case 2:
                        userService.addTask();
                        break;
                    case 3:
                        userService.deleteTaskByName();
                        break;
                    case 4:
                        userService.deleteTaskByID();
                        break;
                    case 5:
                        userService.searchTask();
                        break;
                    case 6:
                        userService.updateTaskByID();
                        break;
                    case 7:
                        userService.assignTaskToUser();
                        break;
                    case 8:
                        userService.showTasksForVisitor();
                    case 9:
                        userService.showTasksInIncreasingOrder();
                    case 10:
                        userService.showTasksInDecreasingOrder();
                    case 11:
                        userService.markTaskAsCompleted();
                    case 12:
                        userService.showCompletedTasks();
                    case 13:
                        userService.showIncompleteTasks();
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());

            }
        }
    }
}