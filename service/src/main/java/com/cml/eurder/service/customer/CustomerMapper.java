package com.cml.eurder.service.customer;

import com.cml.eurder.domain.user.Customer;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    public Collection<CustomerDto> toDto(Collection<Customer> userCollection) {
        return userCollection.stream().map(this::toDto).collect(Collectors.toList());
    }

    public CustomerDto toDto(Customer customer) {
        return new CustomerDto(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getAddress()
        ,customer.getPhoneNumber(), customer.getPassword(), customer.getRole());
    }

    public Customer toCustomer(CreateCustomerDto customerDto) {
        return new Customer(customerDto.getFirstName(), customerDto.getLastName(), customerDto.getEmail(), customerDto.getAddress(),
                customerDto.getPhoneNumber(), customerDto.getPassword(), customerDto.getRole());
    }

    public Customer toCustomer(CustomerDto customerDto) {
        return new Customer(customerDto.getFirstName(), customerDto.getLastName(), customerDto.getEmail(), customerDto.getAddress(),
                customerDto.getPhoneNumber(), customerDto.getPassword(), customerDto.getRole());
    }
}
