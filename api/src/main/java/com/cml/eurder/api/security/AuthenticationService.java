package com.cml.eurder.api.security;

import com.cml.eurder.domain.user.CustomerRepository;
import com.cml.eurder.domain.user.Employee;
import com.cml.eurder.domain.user.EmployeeRepository;
import com.cml.eurder.domain.user.User;
import com.cml.eurder.service.customer.CustomerMapper;
import com.cml.eurder.service.customer.CustomerService;
import com.cml.eurder.service.employee.EmployeeMapper;
import com.cml.eurder.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthenticationService {

    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public AuthenticationService(CustomerRepository customerRepository, EmployeeRepository employeeRepository) {
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
    }

    public User getUser(String email, String password) {
        Employee employee = employeeRepository.findAll().stream()
                .filter(user -> user.getEmail().equals(email))
                .filter(user -> user.getPassword().equals(password))
                .findFirst().orElse(null);
        if(employee != null) return employee;
        else{
          return customerRepository.findAll().stream()
                    .filter(user -> user.getEmail().equals(email))
                    .filter(user -> user.getPassword().equals(password))
                    .findFirst().orElse(null);
        }
    }

    public boolean verifyHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }

}
