package com.infosys.pojo;

public class Task {

    private static int idCounter = 1;

    private final int taskId;
    private volatile String taskTitle;
    private volatile String taskText;
    private volatile String assignedTo;
    private volatile boolean completed = false;

    public Task(String taskTitle, String taskText, String assignedTo) {
        this.taskId = idCounter++;
        this.taskTitle = taskTitle;
        this.taskText = taskText;
        this.assignedTo = assignedTo;
    }

    public synchronized int getTaskId() {
        return taskId;
    }

    public synchronized String getTaskTitle() {
        return taskTitle;
    }

    public synchronized void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public synchronized String getTaskText() {
        return taskText;
    }

    public synchronized void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public synchronized String getAssignedTo() {
        return assignedTo;
    }

    public synchronized void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public synchronized boolean isCompleted() {
        return completed;
    }

    public synchronized void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public synchronized String toString() {
        return "ID: " + taskId + ", Title: " + taskTitle + ", Text: " + taskText + ", Assigned to: " + assignedTo;
    }
}
