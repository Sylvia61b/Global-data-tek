package com.infosys.pojo;

public class User {

    private volatile String username;
    private volatile String password;
    private volatile String role;
    private volatile int userId;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public synchronized String getUsername() {
        return username;
    }

    public synchronized void setUsername(String username) {
        this.username = username;
    }

    public synchronized String getPassword() {
        return password;
    }

    public synchronized void setPassword(String password) {
        this.password = password;
    }

    public synchronized String getRole() {
        return role;
    }

    public synchronized void setRole(String role) {
        this.role = role;
    }

    public synchronized int getUserId() {
        return userId;
    }

    public synchronized void setUserId(int userId) {
        this.userId = userId;
    }
}
