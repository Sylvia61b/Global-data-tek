package com.infosys.dao;

import com.infosys.exception.*;
import com.infosys.pojo.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskDAO {
    private List<Task> taskArray = Collections.synchronizedList(new ArrayList<>());

    // Method to display all tasks
    public List<Task> getTaskArray() {
        synchronized (taskArray) {
            if (taskArray.isEmpty()) {
                return null;
            } else {
                // Return a copy to avoid modification by reference
                return new ArrayList<>(taskArray);
            }
        }
    }

    public synchronized boolean addTask(Task task) throws DuplicateTaskException {
        synchronized (taskArray) {
            if (taskArray.contains(task)) {
                throw new DuplicateTaskException("Task with ID " + task.getTaskId() + " already exists.");
            }
            return taskArray.add(task);
        }
    }

    public synchronized boolean deleteTaskByName(String taskName) throws TaskNotFoundException {
        synchronized (taskArray) {
            Task task = searchTask(taskName);
            if (task != null) {
                taskArray.remove(task);
                return true;
            } else {
                throw new TaskNotFoundException("Task with task name " + taskName + " not found.");
            }
        }
    }

    public synchronized Task deleteTaskById(int id) throws TaskNotFoundException {
        synchronized (taskArray) {
            Task task = findTaskByID(id);
            if (task != null) {
                taskArray.remove(task);
                return task;
            } else {
                throw new TaskNotFoundException("Task with ID " + id + " not found.");
            }
        }
    }

    public synchronized boolean updateTask(int index, String newTask) throws TaskNotFoundException {
        synchronized (taskArray) {
            if (index >= 0 && index < taskArray.size()) {
                for (Task task : taskArray) {
                    if (task.getTaskId() == index) {
                        task.setTaskTitle(newTask);
                        return true;
                    }
                }
            }
            throw new TaskNotFoundException("Task with ID " + index + " not found.");
        }
    }

    public synchronized Task searchTask(String taskName) throws TaskNotFoundException {
        synchronized (taskArray) {
            for (Task task : taskArray) {
                if (task.getTaskTitle().equals(taskName)) {
                    return task;
                }
            }
            throw new TaskNotFoundException("Task with name " + taskName + " not found.");
        }
    }

    public synchronized Task findTaskByID(int taskID) throws TaskNotFoundException {
        synchronized (taskArray) {
            for (Task task : taskArray) {
                if (task.getTaskId() == taskID) {
                    return task;
                }
            }
            throw new TaskNotFoundException("Task with ID " + taskID + " not found.");
        }
    }

    public synchronized void assignTaskToUser(int id, String userName) throws TaskNotFoundException, InvalidTaskOperationException {
        synchronized (taskArray) {
            Task task = findTaskByID(id);
            if (task == null) {
                throw new TaskNotFoundException("Task with ID " + id + " not found.");
            }
            if (task.getAssignedTo() != null) {
                throw new InvalidTaskOperationException("Task with ID " + id + " is already assigned to a user.");
            }
            task.setAssignedTo(userName);
        }
    }

    public synchronized ArrayList<Task> getTasksByUserName(String userName) {
        synchronized (taskArray) {
            ArrayList<Task> userTasks = new ArrayList<>();

            for (Task task : taskArray) {
                if (task.getAssignedTo().equals(userName)) {
                    userTasks.add(task);
                }
            }
            return userTasks;
        }
    }
}
