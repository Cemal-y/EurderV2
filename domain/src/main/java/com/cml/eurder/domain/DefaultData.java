package com.cml.eurder.domain;

import com.cml.eurder.domain.item.Item;
import com.cml.eurder.domain.item.Price;
import com.cml.eurder.domain.order.Order;
import com.cml.eurder.domain.order.OrderItem;
import com.cml.eurder.domain.user.Customer;
import com.cml.eurder.domain.user.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.cml.eurder.domain.item.Currency.EURO;
import static com.cml.eurder.domain.item.Item.ItemBuilder.itemBuilder;
import static com.cml.eurder.domain.user.Address.AddressBuilder.addressBuilder;
import static com.cml.eurder.domain.user.Role.CUSTOMER;
import static com.cml.eurder.domain.user.User.Builder.builder;

@Component
public class DefaultData {
    List<User> defaultCustomers = new ArrayList<>();
    List<Item> defaultItems = new ArrayList<>();
    List<Order> defaultOrders = new ArrayList<>();

    public DefaultData() {
        User customer1 = builder()
                .withFirstName("John")
                .withLastName("Doe")
                .withPhoneNumber("5464646565")
                .withEmail("john@doe.com")
                .withPassword("abc")
                .withAddress(addressBuilder().withCity("Brussel").withStreet("abc").build())
                .withRole(CUSTOMER).build();
        defaultCustomers.add(customer1);

        Item smartphone = itemBuilder().withStockAmount(10).withName("Laptop")
                .withPrice(new Price(700, EURO)).build();
        defaultItems.add(smartphone);

        Item laptop = itemBuilder().withStockAmount(10).withName("Smarthone")
                .withPrice(new Price(700, EURO)).build();
        defaultItems.add(laptop);

        OrderItem orderItem1 = new OrderItem(smartphone, 2);
        OrderItem orderItem2 = new OrderItem(laptop, 1);

        Order order = new Order(customer1, List.of(orderItem1, orderItem2));
        defaultOrders.add(order);
    }

    public List<User> getDefaultCustomers() {
        return defaultCustomers;
    }

    public List<Item> getDefaultItems() {
        return defaultItems;
    }

    public List<Order> getDefaultOrders() {
        return defaultOrders;
    }
}
