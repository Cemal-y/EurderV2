package com.cml.eurder.service.user;

import com.cml.eurder.domain.user.Address;
import com.cml.eurder.domain.user.Role;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.w3c.dom.ls.LSOutput;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private String phoneNumber;
    private String password;
    private Role role;

    public CustomerDto() {
    }

//        @JsonCreator
//    public UserDto(@JsonProperty String id, @JsonProperty String firstName, @JsonProperty String lastName
//            , @JsonProperty String email, @JsonProperty Address address, @JsonProperty  String phoneNumber, @JsonProperty Role role) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.address = address;
//        this.phoneNumber = phoneNumber;
//        this.role = role;
//    }


    public CustomerDto(String id, String firstName, String lastName, String email
            , Address address, String phoneNumber, String password, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
    }


    public String getId() {
        return id;
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
