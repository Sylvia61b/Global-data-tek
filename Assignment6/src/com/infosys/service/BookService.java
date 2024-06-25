package com.infosys.service;

import com.infosys.dao.BookDAO;
import com.infosys.pojo.Book;
import com.infosys.pojo.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookService {
    private final BookDAO bookDAO;

    // Constructor
    public BookService() {
        this.bookDAO = new BookDAO();
    }

    // Method to get details of a book by book ID
    public synchronized Book getBookDetails(User user, Long bookId) {
        // Retrieve all books associated with the user
        List<Book> allBooks = bookDAO.getBooksByUser(user);
        // Search for the book by ID
        for (Book book : allBooks) {
            if (book != null && book.getBookId().equals(bookId)) {
                return book;
            }
        }
        return null; // Book not found
    }

    // Method to print details of a list of books
    public synchronized void printBooks(List<Book> books) {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // Method to select a book by ID
    public synchronized void selectBookById(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book ID: ");
        Long bookId = scanner.nextLong();
        scanner.nextLine(); // consume newline
        Book book = getBookDetails(user, bookId);
        if (book != null) {
            // Print book details
            System.out.println("Book Details: ");
            System.out.println("Name: " + book.getBookName());
            System.out.println("Author: " + book.getAuthorName());
            System.out.println("Description: " + book.getDescription());
        } else {
            System.out.println("Book not found.");
        }
    }
}
