package com.cml.eurder.domain.user;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.UUID;

public class Address {
    private String street;
    private String streetNumber;
    private String postalCode;
    private String city;
    private String id;

    @JsonCreator
    public Address(AddressBuilder addressBuilder) {
        id = UUID.randomUUID().toString();
        street = addressBuilder.street;
        streetNumber = addressBuilder.streetNumber;
        postalCode = addressBuilder.postalCode;
        city = addressBuilder.city;
    }

    public String getStreet() {
        return street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getId() {
        return id;
    }

    public static class AddressBuilder {
        String street;
        String streetNumber;
        String postalCode;
        String city;

            public static AddressBuilder addressBuilder () {
            return new AddressBuilder();
        }

            public Address build () {
            return new Address(this);
        }

            public AddressBuilder withStreet (String street){
            this.street = street;
            return this;
        }

            public AddressBuilder withStreetNumber (String streetNumber){
            this.streetNumber = streetNumber;
            return this;
        }

            public AddressBuilder withPostalCode (String postalCode){
            this.postalCode = postalCode;
            return this;
        }

            public AddressBuilder withCity (String city){
            this.city = city;
            return this;
        }
        }
    }
