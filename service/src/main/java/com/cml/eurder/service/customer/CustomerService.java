package com.cml.eurder.service.customer;

import com.cml.eurder.service.DefaultData;
import com.cml.eurder.domain.exceptions.UserNotFoundException;
import com.cml.eurder.domain.user.Customer;
import com.cml.eurder.domain.user.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class CustomerService {
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;
    private DefaultData defaultData;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper, DefaultData defaultData) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.defaultData = defaultData;
        createDefaultData();
    }

    public Collection<CustomerDto> getAllCustomersInTheDataBase() {
        return customerMapper.toDto(customerRepository.findAll());
    }

    public CustomerDto addCustomer(CreateCustomerDto customerDto){
        return customerMapper.toDto(customerRepository.save(customerMapper.toCustomer(customerDto)));
    }

    public CustomerDto getCustomerById(long id){
        return customerMapper.toDto(customerRepository.findById(id).orElseThrow(() -> new UserNotFoundException("id")));
    }

    private void createDefaultData(){
        for (CreateCustomerDto customer:defaultData.getDefaultCustomers()){
            this.addCustomer(customer);
        }
    }

}
