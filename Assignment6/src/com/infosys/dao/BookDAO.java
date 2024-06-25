package com.infosys.dao;

import com.infosys.pojo.Book;
import com.infosys.pojo.User;

import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private List<Book> books;
    private int bookCount;

    public BookDAO() {
        this.books = new ArrayList<>(); // Initial empty list
        this.bookCount = 0;
    }

    // Add a book to the book list
    public synchronized void addBook(Book book) {
        books.add(book);
        bookCount++;
    }

    // Get a book by its ID
    public synchronized Book getBookById(Long bookId) {
        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }

    // Update a book
    public synchronized boolean updateBook(Book updatedBook) {
        for (int i = 0; i < bookCount; i++) {
            if (books.get(i).getBookId().equals(updatedBook.getBookId())) {
                books.set(i, updatedBook);
                return true;
            }
        }
        return false;
    }

    // Delete a book by its ID
    public synchronized boolean deleteBook(Long bookId) {
        for (int i = 0; i < bookCount; i++) {
            if (books.get(i).getBookId().equals(bookId)) {
                books.remove(i);
                bookCount--;
                return true;
            }
        }
        return false;
    }

    // Retrieve all books
    public synchronized List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    // Retrieve all books for a specific user
    public synchronized List<Book> getBooksByUser(User user) {
        List<Book> userBooks = new ArrayList<>();
        userBooks.addAll(user.getNewBooks());
        userBooks.addAll(user.getFavourite());
        userBooks.addAll(user.getCompleted());
        return userBooks;
    }
}
