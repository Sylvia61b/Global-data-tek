package com.infosys.dao;

import com.infosys.exception.*;
import com.infosys.pojo.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
    private List<Task> taskArray = new ArrayList<>();


    // Method to display all tasks
    public List<Task> getTaskArray() {
        if (taskArray.isEmpty()) {
            return null;
        } else {
            return taskArray;
        }
    }

    public boolean addTask(Task task) throws DuplicateTaskException {
        if (taskArray.contains(task)) {
            throw new DuplicateTaskException("Task with ID " + task.getTaskId() + " already exists.");
        }
        taskArray.add(task);
        return true;
    }

    public boolean deleteTaskByName(String taskName) throws TaskNotFoundException {
        Task task = searchTask(taskName);
        if (task != null) {
            taskArray.remove(task);
            return true;
        }else {
            throw new TaskNotFoundException("Task with task name" + taskName + " not found.");
        }
    }

    public Task deleteTaskById(int id) throws TaskNotFoundException {
        Task task = findTaskByID(id);
        if (task != null) {
            taskArray.remove(task);
            return task;
        }else {
            throw new TaskNotFoundException("Task with ID " + id + " not found.");
        }
    }

    public boolean updateTask(int index, String newTask) throws TaskNotFoundException{
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

    public Task searchTask(String taskName)throws TaskNotFoundException {
        for (Task task : taskArray) {
            if (task.getTaskTitle().equals(taskName)) {
                return task;
            }
        }
        throw new TaskNotFoundException("Task with name " + taskName + " not found.");
    }

    public Task findTaskByID(int taskID)throws TaskNotFoundException {
        for (Task task : taskArray) {
            if (task.getTaskId() == taskID) {
                return task;
            }
        }
        throw new TaskNotFoundException("Task with ID " + taskID + " not found.");
    }

    public void assignTaskToUser(int id, String userName)throws TaskNotFoundException, InvalidTaskOperationException {
        Task task = this.findTaskByID(id);
        if (task == null) {
            throw new TaskNotFoundException("Task with ID " + id + " not found.");
        }
        if (task.getAssignedTo() != null) {
            throw new InvalidTaskOperationException("Task with ID " + id + " is already assigned to a user.");
        }
        task.setAssignedTo(userName);
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

