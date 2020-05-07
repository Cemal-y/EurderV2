package com.cml.eurder.api.controllers;

import com.cml.eurder.service.customer.CreateCustomerDto;
import com.cml.eurder.service.customer.CustomerDto;
import com.cml.eurder.service.customer.CustomerService;
import com.cml.eurder.service.employee.CreateEmployeeDto;
import com.cml.eurder.service.employee.EmployeeDto;
import com.cml.eurder.service.employee.EmployeeService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = EmployeeController.EMPLOYEE_RESOURCE_PATH)
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    public static final String EMPLOYEE_RESOURCE_PATH = "/employees";
    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Get all employees", notes = "A list of all employees will be returned", response = EmployeeDto.class)
    @ResponseStatus(HttpStatus.OK)
    public Collection<EmployeeDto> getAllEmployees() {
        logger.info("Returning all employees");
        return employeeService.getAllEmployeesInTheDataBase();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Create employee", notes = "Create an employee", response = EmployeeDto.class)
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto createEmployee(@RequestBody CreateEmployeeDto employeeDto) {
        logger.info("Returning all employees");
        return employeeService.addEmployee(employeeDto);
    }

}
