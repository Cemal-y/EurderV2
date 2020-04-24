package com.cml.eurder.domain.user;

import com.cml.eurder.domain.DefaultData;
import com.cml.eurder.domain.exceptions.InputCanNotBeNullException;
import com.cml.eurder.domain.exceptions.ItemNotFoundException;
import com.cml.eurder.domain.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import static com.cml.eurder.domain.user.Address.AddressBuilder.addressBuilder;
import static com.cml.eurder.domain.user.Role.CUSTOMER;
import static com.cml.eurder.domain.user.User.Builder.builder;

@Repository
public class CustomerRepository {
    ConcurrentHashMap<String, User> userDatabase;

    DefaultData defaultData;
    @Autowired
    public CustomerRepository(DefaultData defaultData) {
        userDatabase = new ConcurrentHashMap<>();
        this.defaultData = defaultData;
        createDefaultData();
    }

    public User addUser(User user){
        checkIfInputNull(user);
        userDatabase.put(user.getId(), user);
        return user;
    }

    public Collection<User> getAllUsers(){
        return userDatabase.values();
    }

    public User getCustomerById(String id){
        return userDatabase.get(id);
    }

    public ConcurrentHashMap<String, User> getUserDatabase() {
        return userDatabase;
    }

    public static <T> void checkIfInputNull(T input) {
        if (input == null) {
            throw new InputCanNotBeNullException();
        }
    }

    private void createDefaultData(){
        for (User customer:defaultData.getDefaultCustomers()){
            this.addUser(customer);
        }
    }
}
