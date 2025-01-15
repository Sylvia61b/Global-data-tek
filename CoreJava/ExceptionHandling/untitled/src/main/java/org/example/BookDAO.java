package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.Optional;

public class BookDAO {

    public void addBook(Book book) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
            System.out.println("Book added successfully.");
        }
    }

    public Optional<Book> findBookById(int bookId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Book.class, bookId));
        }
    }

    public List<Book> findBookByTitle(String title) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Book WHERE title = :title", Book.class)
                    .setParameter("title", title)
                    .getResultList();
        }
    }

    public void updateBookAvailability(int bookId, String availabilityStatus) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Book book = session.get(Book.class, bookId);
            if (book != null) {
                book.setAvailabilityStatus(availabilityStatus);
                session.update(book);
                transaction.commit();
                System.out.println("Book availability updated successfully.");
            } else {
                System.out.println("Book not found.");
            }
        }
    }

    public void deleteBook(int bookId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Book book = session.get(Book.class, bookId);
            if (book != null) {
                session.delete(book);
                transaction.commit();
                System.out.println("Book deleted successfully.");
            } else {
                System.out.println("Book not found.");
            }
        }
    }
}

