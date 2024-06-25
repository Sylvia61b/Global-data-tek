package com.infosys.pojo;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userName;
    private Long userId;
    private String emailId;
    private String password;
    private List<Book> newBooks;
    private List<Book> favourite;
    private List<Book> completed;

    public User(String userName, Long userId, String emailId, String password) {
        this.userName = userName;
        this.userId = userId;
        this.emailId = emailId;
        this.password = password;
        this.newBooks = new ArrayList<>();
        this.favourite = new ArrayList<>();
        this.completed = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public List<Book> getNewBooks() {
        return newBooks;
    }

    public void setNewBooks(List<Book> newBooks) {
        this.newBooks = newBooks;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Book> getFavourite() {
        return favourite;
    }

    public void setFavourite(List<Book> favourite) {
        this.favourite = favourite;
    }

    public List<Book> getCompleted() {
        return completed;
    }

    public void setCompleted(List<Book> completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userId=" + userId +
                ", emailId='" + emailId + '\'' +
                ", newBooks=" + newBooks +
                ", favourite=" + favourite +
                ", completed=" + completed +
                '}';
    }
}
