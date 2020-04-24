package com.cml.eurder.api.controllers;

import com.cml.eurder.domain.user.CustomerRepository;
import com.cml.eurder.service.user.CustomerDto;
import com.cml.eurder.service.user.CustomerService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = CustomerController.CUSTOMER_RESOURCE_PATH)
public class CustomerController {
    public static final String CUSTOMER_RESOURCE_PATH = "/customers";
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private CustomerService customerService;
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerService customerService, CustomerRepository customerRepository) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
    }

    @PreAuthorize("hasAuthority('VIEW_ALL_CUSTOMERS')")
    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Get all customers", notes = "A list of all customers will be returned", response = CustomerDto.class)
    @ResponseStatus(HttpStatus.OK)
    public Collection<CustomerDto> getAllCustomers() {
        logger.info("Returning all customers");
        return customerService.getAllCustomersInTheDataBase();
    }

    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Create customers", notes = "A new customer will be created", response = CustomerDto.class)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createCustomer(@RequestBody CustomerDto customerDto) {
        logger.info("Creating a new customers");
        return customerService.addCustomer(customerDto);
    }

    @PreAuthorize("hasAuthority('VIEW_DETAILS_OF_CUSTOMER')")
    @GetMapping(path = "/details/{id}", produces = "application/json")
    @ApiOperation(value = "Get customer by id", notes = "Details of a customer will be returned when id is provided", response = CustomerDto.class)
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto getCustomerById(@PathVariable("id") String id) {
        logger.info("Getting a customer by Id");
        return customerService.getCustomerById(id);
    }
}
