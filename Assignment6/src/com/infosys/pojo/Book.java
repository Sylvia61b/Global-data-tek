package com.infosys.pojo;

public class Book {
    private String bookName;
    private String authorName;
    private String description;
    private Long bookId;

    public Book(String bookName, String authorName, String description, Long bookId) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.description = description;
        this.bookId = bookId;
    }

    // Synchronized method to update book details
    public synchronized void updateBookDetails(String bookName, String authorName, String description) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.description = description;
    }

    // Getters for immutable fields
    public String getBookName() {
        return bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getDescription() {
        return description;
    }


    public synchronized Long getBookId() {
        return bookId;
    }

    public synchronized void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public synchronized void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public synchronized void setDescription(String description) {
        this.description = description;
    }

    public synchronized void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", description='" + description + '\'' +
                ", bookId=" + bookId +
                '}';
    }


}
