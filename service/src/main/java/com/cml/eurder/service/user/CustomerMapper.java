package com.cml.eurder.service.user;

import com.cml.eurder.domain.user.User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

import static com.cml.eurder.domain.user.Customer.Builder.customerBuilder;

@Component
public class CustomerMapper {

    public Collection<CustomerDto> toDto(Collection<User> userCollection) {
        return userCollection.stream().map(this::toDto).collect(Collectors.toList());
    }

    public CustomerDto toDto(User user) {
        return new CustomerDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAddress()
        ,user.getPhoneNumber(), user.getPassWord(), user.getRole());
    }

    public User toUser(CustomerDto customerDto) {
        return  customerBuilder()
                .withRole(customerDto.getRole())
                .withAddress(customerDto.getAddress())
                .withEmail(customerDto.getEmail())
                .withFirstName(customerDto.getFirstName())
                .withLastName(customerDto.getLastName())
                .withPhoneNumber(customerDto.getPhoneNumber())
                .withPassword(customerDto.getPassword())
                .build();
    }
}
