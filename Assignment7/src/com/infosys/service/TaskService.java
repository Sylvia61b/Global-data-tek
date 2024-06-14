package com.infosys.service;

import com.infosys.dao.TaskDAO;
import com.infosys.pojo.Task;
import com.infosys.pojo.User;
import com.infosys.exception.*;

import java.util.*;

public class TaskService {

    private Scanner scanner = new Scanner(System.in);
    private TaskDAO taskDAO = new TaskDAO();

    public void showTasks() {
        List<Task> taskArray = taskDAO.getTaskArray();
        if (taskArray != null) {
            for (Task task : taskArray) {
                System.out.println(task);
            }
        } else {
            System.out.println("There is no task.");
        }
    }

    public void showTasks(ArrayList<Task> tasks){
        for(Task task: tasks){
            System.out.println(task.toString());
        }
    }


    public List<Task> getTaskArray(){
        return taskDAO.getTaskArray();
    }

    public void addTask() throws DuplicateTaskException {
        System.out.println("Please enter the number of tasks you would like to add: ");
        int count = 0;
        try {
            count = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine();
            return;
        }
        while (count > 0) {
            System.out.print("Enter task title: ");
            String title = scanner.nextLine();
            System.out.print("Enter task text: ");
            String text = scanner.nextLine();
            System.out.print("Enter assigned to: ");
            String assignedTo = scanner.nextLine();
            Task task = new Task(title, text, assignedTo);
            boolean result = taskDAO.addTask(task);
            if (result) {
                System.out.println("Task added: " + task);
            }
            count--;
        }
    }

    public void deleteTaskByName() {
        System.out.print("Enter name of task to delete: ");
        String taskName = scanner.nextLine();
        try {
            boolean result = taskDAO.deleteTaskByName(taskName);
            if (result) {
                System.out.println("Task " + taskName + " was deleted");
            } else {
                System.out.println("Failed to delete.");
            }
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTaskByID() {
        System.out.print("Enter id of task to delete: ");
        int idToDelete = scanner.nextInt();
        scanner.nextLine();
        try {
            Task removedTask = taskDAO.deleteTaskById(idToDelete);
            if (removedTask != null) {
                System.out.println("Task removed: " + removedTask.getTaskTitle());
            } else {
                System.out.println("Invalid task id.");
            }
        } catch(TaskNotFoundException e){
                System.out.println(e.getMessage());
        }
    }


    public void searchTask() {
        System.out.print("Enter task to search: ");
        String taskToSearch = scanner.nextLine();
        try {
            Task task = taskDAO.searchTask(taskToSearch);
            if (task != null) {
                System.out.println("Task found: " + task);
            }
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateTaskByID() {
        System.out.print("Enter the task id to update: ");
        int idToUpdate = scanner.nextInt();
        System.out.println("Enter new task name to update: ");
        String newTask = scanner.nextLine();
        try {
            boolean result = taskDAO.updateTask(idToUpdate, newTask);
            if (result) {
                System.out.println("Task updated successfully!");
            } else {
                System.out.println("Invalid task index.");
            }
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

//    public String assignTaskToUser() {
//        System.out.print("Enter the task id: ");
//        int id = scanner.nextInt();
//        System.out.println("Enter user name to be assigned to: ");
//        String userName = scanner.nextLine();
//        try {
//            taskDAO.assignTaskToUser(id, userName);
//            return userName;
//        } catch (TaskNotFoundException | UserNotFoundException | InvalidTaskOperationException e) {
//            System.out.println(e.getMessage());
//            return null;
//        }
//    }

    public void assignTaskToUser(User user){
        System.out.print("Enter the task id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            taskDAO.assignTaskToUser(id, user.getUsername());
        } catch (TaskNotFoundException | InvalidTaskOperationException e) {
            System.out.println(e.getMessage());
        }
    }


    public void markTaskAsCompleted(User user) {
        System.out.println("Please enter the task id you want to mark as completed: ");
        int taskId = scanner.nextInt();
        scanner.nextLine();
        ArrayList<Task> tasks = taskDAO.getTasksByUserName(user.getUsername());
        for (Task task : tasks) {
            if (task.getTaskId() == taskId) {
                task.setCompleted(true);
                System.out.println("Task: " + task.getTaskTitle() + " was marked completed.");
                return;
            }
        }
    }

    public void showCompletedTasks(User user){
        ArrayList<Task> tasks = taskDAO.getTasksByUserName(user.getUsername());
        for (Task task : tasks) {
            if (task.isCompleted()) {
                System.out.println("Completed: " + task.toString());
            }
        }
    }

    public void showIncompleteTasks(User user) {
        ArrayList<Task> tasks = taskDAO.getTasksByUserName(user.getUsername());
        for (Task task : tasks) {
            if (!task.isCompleted()) {
                System.out.println("Incomplete: " + task.toString());
            }
        }
    }

    public void showTasksInIncreasingOrder(User user){
        ArrayList<Task> tasks = taskDAO.getTasksByUserName(user.getUsername());
        tasks.sort((task1, task2) -> Integer.compare(task1.getTaskId(), task2.getTaskId()));
        tasks.forEach(task -> System.out.println("Task ID: " + task.getTaskId() + ", Title: " + task.getTaskTitle()));
    }


    public void showTasksInDecreasingOrder(User user){
        ArrayList<Task> tasks = taskDAO.getTasksByUserName(user.getUsername());
        tasks.sort((task1, task2) -> Integer.compare(task2.getTaskId(), task1.getTaskId()));
        tasks.forEach(task -> System.out.println("Task ID: " + task.getTaskId() + ", Title: " + task.getTaskTitle()));
    }

    public ArrayList<Task> getTasksByUserName(String username) {
        ArrayList<Task> userTasks = new ArrayList<>();
        for (Task task : taskDAO.getTaskArray()) {
            if (task.getAssignedTo().equals(username)) {
                userTasks.add(task);
            }
        }
        return userTasks;
    }

}