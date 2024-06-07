package com.infosys.dao;

import com.infosys.pojo.Book;
import com.infosys.pojo.User;

import java.util.Arrays;


public class BookDAO {
    private Book[] books;
    private int bookCount;

    public BookDAO() {
        this.books = new Book[10]; // Initial size
        this.bookCount = 0;
    }

    // Add a book to the book array
    public void addBook(Book book) {
        if (bookCount == books.length) {
            // Double the size of the array
            books = Arrays.copyOf(books, books.length * 2);
        }
        books[bookCount++] = book;
    }

    // Get a book by its ID
    public Book getBookById(Long bookId) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getBookId().equals(bookId)) {
                return books[i];
            }
        }
        return null;
    }

    // Update a book
    public boolean updateBook(Book updatedBook) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getBookId().equals(updatedBook.getBookId())) {
                books[i].setBookName(updatedBook.getBookName());
                books[i].setAuthorName(updatedBook.getAuthorName());
                books[i].setDescription(updatedBook.getDescription());
                return true;
            }
        }
        return false;
    }

    // Delete a book by its ID
    public boolean deleteBook(Long bookId) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getBookId().equals(bookId)) {
                // Move the last book to the current position
                books[i] = books[--bookCount];
                // Remove reference to the last book
                books[bookCount] = null;
                return true;
            }
        }
        return false;
    }

    // Retrieve all books
    public Book[] getAllBooks() {
        return Arrays.copyOf(books, bookCount);
    }

    // Retrieve all books for a specific user
    public Book[] getBooksByUser(User user) {
        Book[] userBooks = new Book[user.getNewBooks().length + user.getFavourite().length + user.getCompleted().length];
        int index = 0;
        for (Book book : user.getNewBooks()) {
            userBooks[index++] = book;
        }
        for (Book book : user.getFavourite()) {
            userBooks[index++] = book;
        }
        for (Book book : user.getCompleted()) {
            userBooks[index++] = book;
        }
        return Arrays.copyOf(userBooks, index);
    }

}

