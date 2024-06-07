package com.infosys.pojo;

import java.util.Arrays;

public class User {
    private String userName;
    private Long userId;
    private String emailId;
    private String password;
    private Book[] newBooks;
    private Book[] favourite;
    private Book[] completed;

    public User(String userName, Long userId, String emailId, String password) {
        this.userName = userName;
        this.userId = userId;
        this.emailId = emailId;
        this.password = password;
        this.newBooks = new Book[10];
        this.favourite = new Book[10];
        this.completed = new Book[10];

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

    public Book[] getNewBooks() {
        return newBooks;
    }

    public void setNewBooks(Book[] newBooks) {
        this.newBooks = newBooks;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Book[] getFavourite() {
        return favourite;
    }

    public void setFavourite(Book[] favourite) {
        this.favourite = favourite;
    }

    public Book[] getCompleted() {
        return completed;
    }

    public void setCompleted(Book[] completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userId=" + userId +
                ", emailId='" + emailId + '\'' +
                ", newBooks=" + Arrays.toString(newBooks) +
                ", favourite=" + Arrays.toString(favourite) +
                ", completed=" + Arrays.toString(completed) +
                '}';
    }
}