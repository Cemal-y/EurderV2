package com.cml.eurder.service.employee;

import com.cml.eurder.domain.exceptions.UserNotFoundException;
import com.cml.eurder.domain.user.EmployeeRepository;
import com.cml.eurder.service.DefaultData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private EmployeeMapper employeeMapper;
    private DefaultData defaultData;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, DefaultData defaultData) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.defaultData = defaultData;
        createDefaultData();
    }

    public Collection<EmployeeDto> getAllEmployeesInTheDataBase() {
        return employeeMapper.toDto(employeeRepository.findAll());
    }

    public EmployeeDto addEmployee(CreateEmployeeDto createEmployeeDto){
        return employeeMapper.toDto(employeeRepository.save(employeeMapper.toEmployee(createEmployeeDto)));
    }

    public EmployeeDto getEmployeeById(long id){
        return employeeMapper.toDto(employeeRepository.findById(id).orElseThrow(() -> new UserNotFoundException("id")));
    }

    private void createDefaultData(){
        for (CreateEmployeeDto employeeDto:defaultData.getDefaultEmployees()){
            this.addEmployee(employeeDto);
        }
    }

}
