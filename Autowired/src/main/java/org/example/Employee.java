package org.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Employee {
    @Autowired
    private Address address;

    public Employee() {
        System.out.println("no-arg constr Employee");
    }

    public Employee(Address address) {
        System.out.println("parameterised Employee constr");
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

