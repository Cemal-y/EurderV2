package com.cml.eurder.service.customer;

import com.cml.eurder.domain.user.Address;
import com.cml.eurder.domain.user.Role;

public class CreateCustomerDto {

    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private String phoneNumber;
    private String password;
    private Role role;

    public CreateCustomerDto() {
    }

    public CreateCustomerDto(String firstName, String lastName, String email
            , Address address, String phoneNumber, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }
}
