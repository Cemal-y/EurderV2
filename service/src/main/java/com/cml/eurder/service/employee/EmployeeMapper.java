package com.cml.eurder.service.employee;

import com.cml.eurder.domain.user.Customer;
import com.cml.eurder.domain.user.Employee;
import com.cml.eurder.service.customer.CreateCustomerDto;
import com.cml.eurder.service.customer.CustomerDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {

    public Collection<EmployeeDto> toDto(Collection<Employee> employeeCollection) {
        return employeeCollection.stream().map(this::toDto).collect(Collectors.toList());
    }

    public EmployeeDto toDto(Employee employee) {
        return new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getAddress()
                ,employee.getPhoneNumber(), employee.getPassword(), employee.getRole());
    }

    public Employee toEmployee(CreateEmployeeDto createEmployeeDto) {
        return new Employee(createEmployeeDto.getFirstName(), createEmployeeDto.getLastName(), createEmployeeDto.getEmail(), createEmployeeDto.getAddress(),
                createEmployeeDto.getPhoneNumber(), createEmployeeDto.getPassword(), createEmployeeDto.getRole());
    }
    public Employee toEmployee(EmployeeDto createEmployeeDto) {
        return new Employee(createEmployeeDto.getFirstName(), createEmployeeDto.getLastName(), createEmployeeDto.getEmail(), createEmployeeDto.getAddress(),
                createEmployeeDto.getPhoneNumber(), createEmployeeDto.getPassword(), createEmployeeDto.getRole());
    }
}
