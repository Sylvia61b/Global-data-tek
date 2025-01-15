package org.example;

import java.util.Optional;
import java.util.Scanner;

public class EmployeeTest {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Scanner scanner = new Scanner(System.in);

        try {
            // Add a new employee
            Employee newEmployee = new Employee();
            newEmployee.setName("Employee1");
            newEmployee.setDepartment("Engineering");
            newEmployee.setDesignation("Developer");
            newEmployee.setSalary(50000);
            employeeDAO.saveEmployee(newEmployee);
            System.out.println("Employee saved successfully.");

            // Retrieve employee by ID
            System.out.print("Enter Employee ID to retrieve: ");
            int employeeId = scanner.nextInt();
            Optional<Employee> optionalEmployee = employeeDAO.getEmployeeById(employeeId);
            optionalEmployee.ifPresentOrElse(
                    emp -> System.out.println("Employee Found: " + emp.getName()),
                    () -> System.out.println("Employee not found.")
            );

            // Update employee's designation and salary
            System.out.print("Enter Employee ID to update: ");
            employeeId = scanner.nextInt();
            System.out.print("Enter new designation: ");
            String designation = scanner.next();
            System.out.print("Enter new salary: ");
            double salary = scanner.nextDouble();
            employeeDAO.updateEmployee(employeeId, designation, salary);
            System.out.println("Employee updated successfully.");

            // Delete employee by ID
            System.out.print("Enter Employee ID to delete: ");
            employeeId = scanner.nextInt();
            employeeDAO.deleteEmployee(employeeId);
            System.out.println("Employee deleted successfully.");

        } finally {
            // Close the scanner
            scanner.close();
            System.out.println("All operations completed.");
        }
    }
}

