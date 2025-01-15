package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Optional;

public class EmployeeDAO {

    // Create a new employee record
    public void saveEmployee(Employee employee) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        }
    }

    // Retrieve employee details by EmployeeID
    public Optional<Employee> getEmployeeById(int employeeId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Employee employee = session.get(Employee.class, employeeId);
            return Optional.ofNullable(employee);
        }
    }

    // Update an employee's designation and salary
    public void updateEmployee(int employeeId, String newDesignation, double newSalary) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, employeeId);
            if (employee != null) {
                employee.setDesignation(newDesignation);
                employee.setSalary(newSalary);
                session.update(employee);
                transaction.commit();
            }
        }
    }

    // Delete an employee record using EmployeeID
    public void deleteEmployee(int employeeId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, employeeId);
            if (employee != null) {
                session.delete(employee);
                transaction.commit();
            }
        }
    }
}

