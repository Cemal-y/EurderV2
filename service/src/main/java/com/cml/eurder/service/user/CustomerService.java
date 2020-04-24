package com.cml.eurder.service.user;

import com.cml.eurder.domain.user.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public Collection<CustomerDto> getAllCustomersInTheDataBase() {
        return customerMapper.toDto(customerRepository.getAllUsers());
    }

    public CustomerDto addCustomer(CustomerDto customerDto){
        return customerMapper.toDto(customerRepository.addUser(customerMapper.toUser(customerDto)));
    }

    public CustomerDto getCustomerById(String id){
        return customerMapper.toDto(customerRepository.getCustomerById(id));
    }

}
