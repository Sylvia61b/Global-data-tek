package com.infosys.service;

import com.infosys.dao.TaskDAO;
import com.infosys.pojo.Task;
import com.infosys.pojo.User;
import com.infosys.exception.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TaskService {

    private final TaskDAO taskDAO = new TaskDAO();
    private final ThreadLocal<Scanner> scannerThreadLocal = ThreadLocal.withInitial(() -> new Scanner(System.in));

    public void showTasks() {
        synchronized (taskDAO) {
            List<Task> taskArray = taskDAO.getTaskArray();
            if (taskArray != null) {
                for (Task task : taskArray) {
                    System.out.println(task);
                }
            } else {
                System.out.println("There are no tasks.");
            }
        }
    }

    public void showTasks(List<Task> tasks) {
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public List<Task> getTaskArray() {
        synchronized (taskDAO) {
            return taskDAO.getTaskArray();
        }
    }

    public synchronized void addTask() throws DuplicateTaskException {
        Scanner scanner = scannerThreadLocal.get();
        System.out.println("Please enter the number of tasks you would like to add: ");
        int count = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        while (count > 0) {
            System.out.print("Enter task title: ");
            String title = scanner.nextLine();
            System.out.print("Enter task text: ");
            String text = scanner.nextLine();
            System.out.print("Enter assigned to: ");
            String assignedTo = scanner.nextLine();
            Task task = new Task(title, text, assignedTo);
            synchronized (taskDAO) {
                boolean result = taskDAO.addTask(task);
                if (result) {
                    System.out.println("Task added: " + task);
                }
            }
            count--;
        }
    }

    public synchronized void deleteTaskByName() {
        Scanner scanner = scannerThreadLocal.get();
        System.out.print("Enter name of task to delete: ");
        String taskName = scanner.nextLine();
        try {
            synchronized (taskDAO) {
                boolean result = taskDAO.deleteTaskByName(taskName);
                if (result) {
                    System.out.println("Task " + taskName + " was deleted");
                } else {
                    System.out.println("Failed to delete.");
                }
            }
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public synchronized void deleteTaskByID() {
        Scanner scanner = scannerThreadLocal.get();
        System.out.print("Enter id of task to delete: ");
        int idToDelete = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        try {
            synchronized (taskDAO) {
                Task removedTask = taskDAO.deleteTaskById(idToDelete);
                if (removedTask != null) {
                    System.out.println("Task removed: " + removedTask.getTaskTitle());
                } else {
                    System.out.println("Invalid task id.");
                }
            }
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public synchronized void searchTask() {
        Scanner scanner = scannerThreadLocal.get();
        System.out.print("Enter task to search: ");
        String taskToSearch = scanner.nextLine();
        try {
            synchronized (taskDAO) {
                Task task = taskDAO.searchTask(taskToSearch);
                if (task != null) {
                    System.out.println("Task found: " + task);
                }
            }
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public synchronized void updateTaskByID() {
        Scanner scanner = scannerThreadLocal.get();
        System.out.print("Enter the task id to update: ");
        int idToUpdate = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        System.out.print("Enter new task name to update: ");
        String newTask = scanner.nextLine();
        try {
            synchronized (taskDAO) {
                boolean result = taskDAO.updateTask(idToUpdate, newTask);
                if (result) {
                    System.out.println("Task updated successfully!");
                } else {
                    System.out.println("Invalid task index.");
                }
            }
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public synchronized void assignTaskToUser(User user) {
        Scanner scanner = scannerThreadLocal.get();
        System.out.print("Enter the task id: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        try {
            synchronized (taskDAO) {
                taskDAO.assignTaskToUser(id, user.getUsername());
            }
        } catch (TaskNotFoundException | InvalidTaskOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    public synchronized void markTaskAsCompleted(User user) {
        Scanner scanner = scannerThreadLocal.get();
        System.out.print("Please enter the task id you want to mark as completed: ");
        int taskId = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        ArrayList<Task> tasks = taskDAO.getTasksByUserName(user.getUsername());
        for (Task task : tasks) {
            if (task.getTaskId() == taskId) {
                synchronized (task) {
                    task.setCompleted(true);
                    System.out.println("Task: " + task.getTaskTitle() + " was marked completed.");
                }
                return;
            }
        }
    }

    public synchronized void showCompletedTasks(User user) {
        ArrayList<Task> tasks = taskDAO.getTasksByUserName(user.getUsername());
        for (Task task : tasks) {
            if (task.isCompleted()) {
                System.out.println("Completed: " + task);
            }
        }
    }

    public synchronized void showIncompleteTasks(User user) {
        ArrayList<Task> tasks = taskDAO.getTasksByUserName(user.getUsername());
        for (Task task : tasks) {
            if (!task.isCompleted()) {
                System.out.println("Incomplete: " + task);
            }
        }
    }

    public synchronized void showTasksInIncreasingOrder(User user) {
        ArrayList<Task> tasks = taskDAO.getTasksByUserName(user.getUsername());
        tasks.sort((task1, task2) -> Integer.compare(task1.getTaskId(), task2.getTaskId()));
        tasks.forEach(task -> System.out.println("Task ID: " + task.getTaskId() + ", Title: " + task.getTaskTitle()));
    }

    public synchronized void showTasksInDecreasingOrder(User user) {
        ArrayList<Task> tasks = taskDAO.getTasksByUserName(user.getUsername());
        tasks.sort((task1, task2) -> Integer.compare(task2.getTaskId(), task1.getTaskId()));
        tasks.forEach(task -> System.out.println("Task ID: " + task.getTaskId() + ", Title: " + task.getTaskTitle()));
    }

    public synchronized ArrayList<Task> getTasksByUserName(String username) {
        ArrayList<Task> userTasks = new ArrayList<>();
        synchronized (taskDAO) {
            for (Task task : taskDAO.getTaskArray()) {
                if (task.getAssignedTo().equals(username)) {
                    userTasks.add(task);
                }
            }
        }
        return userTasks;
    }
}
