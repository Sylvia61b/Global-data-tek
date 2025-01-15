package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.example.Employee;

import java.util.Optional;
import java.util.Scanner;

public class Test
{
    public static void main(String[] args)
    {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();

        Employee employee = new Employee();
        employee.setName("Madhu");
        employee.setSalary(45000);

        session.save(employee);
        transaction.commit();
        session.close();


        System.out.println("Hibernate using XML Done");
    }
}
