package org.example;


import java.util.Date;
import java.util.List;

public class StudentTest {
    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();

        // Register a new student
        studentDAO.registerStudent("Alice Smith", 20, "Computer Science", new Date());

        // Fetch all students
        List<Student> students = studentDAO.getAllStudents();
        students.forEach(student -> System.out.println(student.getName()));

        // Find students by program
        List<Student> csStudents = studentDAO.getStudentsByProgram("Computer Science");
        csStudents.forEach(student -> System.out.println("CS Student: " + student.getName()));

        // Update a student’s program
        studentDAO.updateStudentProgram(1, "Data Science");

        // Delete a student
        studentDAO.deleteStudent(1);
    }
}

