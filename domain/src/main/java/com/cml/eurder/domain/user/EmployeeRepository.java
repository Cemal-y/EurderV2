package com.cml.eurder.domain.user;

import com.cml.eurder.domain.exceptions.InputCanNotBeNullException;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import static com.cml.eurder.domain.user.Address.AddressBuilder.addressBuilder;
import static com.cml.eurder.domain.user.Role.ADMIN;
import static com.cml.eurder.domain.user.Role.CUSTOMER;
import static com.cml.eurder.domain.user.User.Builder.builder;

@Repository
public class EmployeeRepository {
    ConcurrentHashMap<String, User> employeeDatabase;

    public EmployeeRepository() {
        employeeDatabase = new ConcurrentHashMap<>();
        createDefaultData();
    }

    public User addEmployee(User employee){
        checkIfInputNull(employee);
        employeeDatabase.put(employee.getId(), employee);
        return employee;
    }

    public Collection<User> getAllUsers(){
        return employeeDatabase.values();
    }

    public User getCustomerById(String id){
        return employeeDatabase.get(id);
    }

    public ConcurrentHashMap<String, User> getUserDatabase() {
        return employeeDatabase;
    }

    public static <T> void checkIfInputNull(T input) {
        if (input == null) {
            throw new InputCanNotBeNullException();
        }
    }

    private void createDefaultData(){
        User employee1 = builder()
                .withFirstName("John")
                .withLastName("Snow")
                .withPhoneNumber("4894889449")
                .withEmail("john@snow.com")
                .withPassword("abc")
                .withAddress(addressBuilder().withCity("Brussel").withStreet("abc").build())
                .withRole(ADMIN).build();
        employeeDatabase.put(employee1.getId(), employee1);
    }
}
