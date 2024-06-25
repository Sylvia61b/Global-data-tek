package com.infosys.service;

import com.infosys.dao.UserDAO;
import com.infosys.exception.DuplicateTaskException;
import com.infosys.exception.DuplicateUserException;
import com.infosys.exception.UserNotFoundException;
import com.infosys.pojo.Task;
import com.infosys.pojo.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class UserService {
    private TaskService taskService;
    private UserDAO userDAO;
    private Scanner scanner = new Scanner(System.in);

    // Use ConcurrentMap for thread-safe user session management
    private ConcurrentMap<String, User> loggedInUsers = new ConcurrentHashMap<>();

    public UserService(TaskService taskService) {
        this.taskService = taskService;
        this.userDAO = new UserDAO();
    }

    // Register a new user
    public synchronized void registerUser(String username, String password) {
        try {
            userDAO.addUser(new User(username, password, "Client"));
            System.out.println("User registered successfully.");
        } catch (DuplicateUserException e) {
            System.out.println(e.getMessage());
        }
    }

    // Log in a user
    public synchronized boolean loginUser(String username, String password) {
        try {
            User user = userDAO.getUserByUsername(username);
            if (user != null && user.getPassword().equals(password)) {
                loggedInUsers.put(username, user);
                return true;
            }
            System.out.println("Invalid username or password. Please try again.");
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    // Log out a user
    public synchronized void logoutUser(String username) {
        loggedInUsers.remove(username);
    }

    // Get user details by username
    public User getUserDetails(String username) throws UserNotFoundException {
        return userDAO.getUserByUsername(username);
    }

    // Get user by user ID
    public User getUserById(int userId) throws UserNotFoundException {
        return userDAO.getUserById(userId);
    }

    // Check if a user is logged in with a specific role
    private boolean isLoggedInWithRole(String username, String role) {
        User user = loggedInUsers.get(username);
        return user != null && user.getRole().equals(role);
    }

    public void showTasks(String username) {
        if (isLoggedInWithRole(username, "Client")) {
            taskService.showTasks();
        } else {
            System.out.println("Only clients can show tasks.");
        }
    }

    public void addTask(String username) {
        if (isLoggedInWithRole(username, "Client")) {
            try {
                taskService.addTask();
            } catch (DuplicateTaskException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Only clients can add tasks.");
        }
    }

    public void updateTaskByID(String username) {
        if (isLoggedInWithRole(username, "Client")) {
            taskService.updateTaskByID();
        } else {
            System.out.println("Only clients can update tasks.");
        }
    }

    public void deleteTaskByID(String username) {
        if (isLoggedInWithRole(username, "Client")) {
            taskService.deleteTaskByID();
        } else {
            System.out.println("Only clients can delete tasks.");
        }
    }

    public void deleteTaskByName(String username) {
        if (isLoggedInWithRole(username, "Client")) {
            taskService.deleteTaskByName();
        } else {
            System.out.println("Only clients can delete tasks.");
        }
    }

    public void searchTask(String username) {
        if (isLoggedInWithRole(username, "Client")) {
            taskService.searchTask();
        } else {
            System.out.println("Only clients can search tasks.");
        }
    }

    public void assignTaskToUser(String username) {
        if (isLoggedInWithRole(username, "Client")) {
            System.out.println("Enter user name to be assigned to: ");
            String visitorName = scanner.nextLine();
            try {
                User visitorUser = userDAO.getUserByUsername(visitorName);
                taskService.assignTaskToUser(visitorUser);
            } catch (UserNotFoundException e) {
                System.out.println(e.getMessage());
                try {
                    User visitorUser = new User(visitorName, "visitorPassword", "Visitor");
                    userDAO.addUser(visitorUser);
                    taskService.assignTaskToUser(visitorUser);
                } catch (DuplicateUserException ex) {
                    System.out.println(ex.getMessage());
                }
            }

        } else {
            System.out.println("Only clients can assign tasks.");
        }
    }

    public List<Task> getTasksForVisitor(String username) {
        if (isLoggedInWithRole(username, "Visitor")) {
            return taskService.getTasksByUserName(username);
        } else {
            System.out.println("Only visitors can view their tasks.");
            return null;
        }
    }

    public void showTasksForVisitor(String username) {
        List<Task> tasks = getTasksForVisitor(username);
        if (tasks != null) {
            taskService.showTasks(tasks);
        }
    }

    public void markTaskAsCompleted(String username) {
        if (isLoggedInWithRole(username, "Visitor")) {
            taskService.markTaskAsCompleted(loggedInUsers.get(username));
        }
    }

    public void showTasksInIncreasingOrder(String username) {
        if (loggedInUsers.get(username) != null) {
            taskService.showTasksInIncreasingOrder(loggedInUsers.get(username));
        }
    }

    public void showTasksInDecreasingOrder(String username) {
        if (loggedInUsers.get(username) != null) {
            taskService.showTasksInDecreasingOrder(loggedInUsers.get(username));
        }
    }

    public void showCompletedTasks(String username) {
        if (isLoggedInWithRole(username, "Visitor")) {
            taskService.showCompletedTasks(loggedInUsers.get(username));
        }
    }

    public void showIncompleteTasks(String username) {
        if (isLoggedInWithRole(username, "Visitor")) {
            taskService.showIncompleteTasks(loggedInUsers.get(username));
        }
    }
}


