package com.cml.eurder.service;

import com.cml.eurder.domain.item.Item;
import com.cml.eurder.domain.order.Order;
import com.cml.eurder.domain.order.OrderItem;
import com.cml.eurder.domain.user.*;
import com.cml.eurder.service.customer.CreateCustomerDto;
import com.cml.eurder.service.customer.CustomerDto;
import com.cml.eurder.service.employee.CreateEmployeeDto;
import com.cml.eurder.service.employee.EmployeeDto;
import com.cml.eurder.service.item.CreateItemDto;
import com.cml.eurder.service.item.ItemDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.cml.eurder.domain.item.Currencies.EURO;
import static com.cml.eurder.domain.item.Currencies.US_DOLLARS;
import static com.cml.eurder.domain.user.Role.CUSTOMER;


@Component
public class DefaultData {
    List<CreateCustomerDto> defaultCustomers = new ArrayList<>();
    List<CreateItemDto> defaultItems = new ArrayList<>();
    List<Order> defaultOrders = new ArrayList<>();
    List<CreateEmployeeDto> defaultEmployees = new ArrayList<>();


    //    private void createDefaultData(){
//        for (User customer:defaultData.getDefaultCustomers()){
//            this.addUser(customer);
//        }
//    }


    public DefaultData() {


        CreateCustomerDto customer1 = new CreateCustomerDto("John", "Doe", "john@doe.com",
                new Address("abc", "12", "1000", "Brussels"),
                "5464646565",  "abc", Role.CUSTOMER);
        defaultCustomers.add(customer1);
        CreateEmployeeDto employee = new CreateEmployeeDto("John", "Snow", "john@snow.com",
                new Address("abc", "12", "1000", "Brussels"),
                "777446565",  "abc", Role.ADMIN);
        defaultEmployees.add(employee);
        CreateEmployeeDto employee2 = new CreateEmployeeDto("admin", "admin", "admin",
                new Address("abc", "12", "1000", "Brussels"),
                "777446565",  "admin", Role.ADMIN);
        defaultEmployees.add(employee2);
        CreateItemDto smartphone = new CreateItemDto("Laptop", "description", 10, 500, US_DOLLARS);
        defaultItems.add(smartphone);
        CreateItemDto laptop = new CreateItemDto("Smarthone", "description", 100, 800, EURO);
        defaultItems.add(laptop);

//        OrderItem orderItem1 = new OrderItem(smartphone, 2);
//        OrderItem orderItem2 = new OrderItem(laptop, 1);
//
//        Order order = new Order(customer1, List.of(orderItem1, orderItem2));
//        defaultOrders.add(order);
    }

    public List<CreateEmployeeDto> getDefaultEmployees() {
        return defaultEmployees;
    }

    public List<CreateCustomerDto> getDefaultCustomers() {
        return defaultCustomers;
    }

    public List<CreateItemDto> getDefaultItems() {
        return defaultItems;
    }

//    public List<Order> getDefaultOrders() {
//        return defaultOrders;
//    }
}
