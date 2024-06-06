package com.infosys.dao;

import com.infosys.pojo.Task;

import java.util.ArrayList;

public class TaskDAO {
    ArrayList<Task> taskArray = new ArrayList<>();

    public TaskDAO() {
    }

    // Method to display all tasks
    public ArrayList<Task> getTaskArray() {
        if (taskArray.isEmpty()) {
            return null;
        } else {
            return taskArray;
        }
    }

    public boolean addTask(Task task) {
        taskArray.add(task);
        return true;
    }

    public boolean deleteTaskByName(String taskName) {
        Task task = searchTask(taskName);
        if (task != null) {
            taskArray.remove(task);
            return true;
        }
        return false;
    }

    public Task deleteTaskById(int id) {
        Task task = findTaskByID(id);
        if (task != null) {
            taskArray.remove(task);
            return task;
        }
        return null;
    }

    public boolean updateTask(int index, String newTask) {
        if (index >= 0 && index < taskArray.size()) {
            for (Task task : taskArray) {
                if (task.getTaskId() == index) {
                    task.setTaskTitle(newTask);
                    return true;
                }
            }
        }
        return false;
    }

    public Task searchTask(String taskName) {
        for (Task task : taskArray) {
            if (task.getTaskTitle().equals(taskName)) {
                return task;
            }
        }
        return null;
    }

    public Task findTaskByID(int taskID) {
        for (Task task : taskArray) {
            if (task.getTaskId() == taskID) {
                return task;
            }
        }
        return null;
    }

    public void assignTaskToUser(int id, String userName) {
        Task task = findTaskByID(id);
        if (task != null) {
            task.setAssignedTo(userName);
        }
    }

    public ArrayList<Task> getTasksByUserName(String userName) {
        ArrayList<Task> userTasks = new ArrayList<>();

        for (Task task : taskArray) {
            if (task.getAssignedTo().equals(userName)) {
                userTasks.add(task);
            }
        }
        return userTasks;
    }
}
