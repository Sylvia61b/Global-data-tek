package com.infosys.controller;

import com.infosys.service.TaskService;
import com.infosys.service.UserService;
import com.infosys.pojo.User;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInteractionHandler implements Runnable {
    private Scanner scanner = new Scanner(System.in);
    private UserService userService;
    private TaskService taskService;
    private User user;

    public UserInteractionHandler(UserService userService, TaskService taskService, User user) {
        this.userService = userService;
        this.taskService = taskService;
        this.user = user;
    }

    @Override
    public void run() {
        userMenu();
    }


    // Method for user-specific menu
    private void userMenu() {
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


            try {
                switch (choice) {
                    case 0:
                        System.out.println("Exiting Todo Manager.");
                        return;
                    case 1:
                        userService.showTasks(user.getUsername());
                        break;
                    case 2:
                        userService.addTask(user.getUsername());
                        break;
                    case 3:
                        userService.deleteTaskByName(user.getUsername());
                        break;
                    case 4:
                        userService.deleteTaskByID(user.getUsername());
                        break;
                    case 5:
                        userService.searchTask(user.getUsername());
                        break;
                    case 6:
                        userService.updateTaskByID(user.getUsername());
                        break;
                    case 7:
                        userService.assignTaskToUser(user.getUsername());
                        break;
                    case 8:
                        userService.showTasksForVisitor(user.getUsername());
                    case 9:
                        userService.showTasksInIncreasingOrder(user.getUsername());
                    case 10:
                        userService.showTasksInDecreasingOrder(user.getUsername());
                    case 11:
                        userService.markTaskAsCompleted(user.getUsername());
                    case 12:
                        userService.showCompletedTasks(user.getUsername());
                    case 13:
                        userService.showIncompleteTasks(user.getUsername());
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());

            }
        }
    }
}
