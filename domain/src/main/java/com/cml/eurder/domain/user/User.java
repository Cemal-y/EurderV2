package com.cml.eurder.domain.user;

import java.util.UUID;

public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private String phoneNumber;
    private String passWord;
    private Role role;

    public User(Builder<?> builder) {
        this.id = UUID.randomUUID().toString();
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.address = builder.address;
        this.passWord = builder.password;
        this.phoneNumber = builder.phoneNumber;
        this.role = builder.role;
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

    public String getPassWord() {
        return passWord;
    }

    public static abstract class Builder<T extends User> {
        private String firstName;
        private String lastName;
        private String email;
        private Address address;
        private String phoneNumber;
        private String password;
        public Role role;

        public Builder() {
        }

        public static Builder<?> builder() {
            return new Builder<User>() {
                @Override
                public User build() {
                    return new User(this);
                }
            };
        }
        public abstract T build();

        public Builder<T> withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public Builder<T> withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        public Builder<T> withEmail(String email) {
            this.email = email;
            return this;
        }
        public Builder<T> withAddress(Address address) {
            this.address = address;
            return this;
        }
        public Builder<T> withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }
        public Builder<T> withRole(Role role) {
            this.role = role;
            return this;
        }
        public Builder<T> withPassword(String password) {
            this.password = password;
            return this;
        }
    }

}
