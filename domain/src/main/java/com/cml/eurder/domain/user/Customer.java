package com.cml.eurder.domain.user;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer extends User {
    public Customer() {
    }

    public Customer(long id, String firstName, String lastName, String email, Address address, String phoneNumber, String password, Role role) {
        super(id, firstName, lastName, email, address, phoneNumber, password, role);
    }

    public Customer(String firstName, String lastName, String email, Address address, String phoneNumber, String password, Role role) {
        super(firstName, lastName, email, address, phoneNumber, password, role);
    }


}
