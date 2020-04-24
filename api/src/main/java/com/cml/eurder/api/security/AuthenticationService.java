package com.cml.eurder.api.security;

import com.cml.eurder.domain.user.CustomerRepository;
import com.cml.eurder.domain.user.EmployeeRepository;
import com.cml.eurder.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService {

    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

    private List<User> usersList = new ArrayList<>();

    @Autowired
    public AuthenticationService(CustomerRepository customerRepository, EmployeeRepository employeeRepository) {
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        usersList.addAll(customerRepository.getAllUsers());
        usersList.addAll(employeeRepository.getAllUsers());
    }

    public User getUser(String email, String password) {
        return usersList.stream()
                .filter(user -> user.getEmail().equals(email))
                .filter(user -> user.getPassWord().equals(password))
                .findFirst()
                .orElse(null);
    }

    public boolean verifyHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}
