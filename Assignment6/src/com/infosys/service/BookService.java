package com.infosys.service;

import com.infosys.dao.BookDAO;
import com.infosys.pojo.Book;
import com.infosys.pojo.User;

import java.util.Scanner;

public class BookService {
    private Scanner scanner;
    private BookDAO bookDAO;

    // Constructor
    public BookService() {
        this.scanner = new Scanner(System.in);
        this.bookDAO = new BookDAO();
    }

    // Method to get details of a book by book ID
    public Book getBookDetails(User user, Long bookId) {
        // Merge all book arrays of the user
        Book[] allBooks = mergeArrays(user.getNewBooks(), user.getFavourite(), user.getCompleted());
        // Search for the book by ID
        for (Book book : allBooks) {
            if (book!=null && book.getBookId().equals(bookId)) {
                return book;
            }
        }
        return null; // Book not found
    }

    // Helper method to merge multiple arrays of books
    private Book[] mergeArrays(Book[]... arrays) {
        int totalLength = 0;
        for (Book[] array : arrays) {
            totalLength += array.length;
        }

        Book[] result = new Book[totalLength];
        int currentIndex = 0;
        for (Book[] array : arrays) {
            for (Book book : array) {
                result[currentIndex++] = book;
            }
        }
        return result;
    }

    // Method to print details of an array of books
    public void printBooks(Book[] books) {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // Method to select a book by ID
    public void selectBookById(User user) {
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

