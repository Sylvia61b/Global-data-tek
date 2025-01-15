package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.Date;
import java.util.List;

public class StudentDAO {

    public void registerStudent(String name, int age, String program, Date enrollmentDate) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Student student = new Student();
            student.setName(name);
            student.setAge(age);
            student.setProgram(program);
            student.setEnrollmentDate(enrollmentDate);
            session.save(student);
            transaction.commit();
            System.out.println("Student registered successfully.");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Student> getAllStudents() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Student> students = session.createQuery("FROM Student", Student.class).list();
        session.close();
        return students;
    }

    public List<Student> getStudentsByProgram(String program) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Student> query = session.createQuery("FROM Student WHERE program = :program", Student.class);
        query.setParameter("program", program);
        List<Student> students = query.list();
        session.close();
        return students;
    }

    public List<Student> getStudentsByEnrollmentYear(int year) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Student> query = session.createQuery("FROM Student WHERE YEAR(enrollmentDate) = :year", Student.class);
        query.setParameter("year", year);
        List<Student> students = query.list();
        session.close();
        return students;
    }

    public void updateStudentProgram(int studentId, String newProgram) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Student student = session.get(Student.class, studentId);
            if (student != null) {
                student.setProgram(newProgram);
                session.update(student);
                transaction.commit();
                System.out.println("Student program updated successfully.");
            } else {
                System.out.println("Student not found.");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteStudent(int studentId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Student student = session.get(Student.class, studentId);
            if (student != null) {
                session.delete(student);
                transaction.commit();
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Student not found.");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
