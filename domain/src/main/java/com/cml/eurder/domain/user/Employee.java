package com.cml.eurder.domain.user;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee extends User {
    public Employee() {
    }

    public Employee(long id, String firstName, String lastName, String email, Address address, String phoneNumber, String password, Role role) {
        super(id, firstName, lastName, email, address, phoneNumber, password, role);
    }

    public Employee(String firstName, String lastName, String email, Address address, String phoneNumber, String password, Role role) {
        super(firstName, lastName, email, address, phoneNumber, password, role);
    }
}
