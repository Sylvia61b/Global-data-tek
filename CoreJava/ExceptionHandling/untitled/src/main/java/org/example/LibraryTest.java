package org.example;

import java.util.Optional;
import java.util.Scanner;

public class LibraryTest {
    public static void main(String[] args) {
        BookDAO bookDAO = new BookDAO();
        Scanner scanner = new Scanner(System.in);

        // Add a new book
        Book book = new Book();
        book.setTitle("Effective Java");
        book.setAuthor("Joshua Bloch");
        book.setGenre("Programming");
        book.setPublishedYear(2018);
        book.setAvailabilityStatus("Available");
        bookDAO.addBook(book);

        // Find a book by ID
        System.out.print("Enter Book ID to find: ");
        int bookId = scanner.nextInt();
        Optional<Book> foundBook = bookDAO.findBookById(bookId);
        foundBook.ifPresent(b -> System.out.println("Book Found: " + b.getTitle()));

        // Find books by title
        System.out.print("Enter Book Title to find: ");
        String title = scanner.next();
        bookDAO.findBookByTitle(title).forEach(b -> System.out.println("Found: " + b.getTitle()));

        // Update book availability status
        System.out.print("Enter Book ID to update availability: ");
        bookId = scanner.nextInt();
        System.out.print("Enter new availability status (Available/Checked Out): ");
        String status = scanner.next();
        bookDAO.updateBookAvailability(bookId, status);

        // Delete a book
        System.out.print("Enter Book ID to delete: ");
        bookId = scanner.nextInt();
        bookDAO.deleteBook(bookId);

        scanner.close();
        HibernateUtil.shutdown();
    }
}

