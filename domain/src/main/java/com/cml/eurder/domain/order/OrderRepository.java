package com.cml.eurder.domain.order;

import com.cml.eurder.domain.DefaultData;
import com.cml.eurder.domain.exceptions.InputCanNotBeNullException;
import com.cml.eurder.domain.exceptions.OrderNotFoundException;
import com.cml.eurder.domain.item.Currency;
import com.cml.eurder.domain.item.Item;
import com.cml.eurder.domain.item.ItemRepository;
import com.cml.eurder.domain.item.Price;
import com.cml.eurder.domain.user.Customer;
import com.cml.eurder.domain.user.CustomerRepository;
import com.cml.eurder.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static com.cml.eurder.domain.item.Currency.EURO;
import static com.cml.eurder.domain.item.Item.ItemBuilder.itemBuilder;
import static com.cml.eurder.domain.order.OrderState.DELIVERED;
import static com.cml.eurder.domain.order.OrderState.IN_PROGRESS;
import static com.cml.eurder.domain.user.Customer.Builder.customerBuilder;

@Repository
public class OrderRepository {
    ConcurrentHashMap<String, Order> orderDatabase;

    DefaultData defaultData;

    @Autowired
    public OrderRepository(DefaultData defaultData) {
        this.orderDatabase = new ConcurrentHashMap<>();
        this.defaultData = defaultData;
        createDefaultData();
    }

    public Order createOrder(Order order){
        checkIfInputNull(order);
        decreaseStockAmounts(order);
        orderDatabase.put(order.getID(), order);
        return order;
    }

    private void decreaseStockAmounts(Order order) {
        order.getItemsWithAmount().stream()
                .forEach(orderItem -> orderItem.getItem().deductFromStockAmount(orderItem.getItemAmount()));
    }

    public Collection<Order> getOrdersOfACustomer(String customerId){
       return orderDatabase.values().stream()
                .filter(order -> order.getCustomer().getId().equals(customerId))
                .collect(Collectors.toList());
    }

    public Order reorderAPreviousOrder(String orderId){
        Order previousOrder =  getOrderById(orderId);
        Order newOrder = new Order(previousOrder.getCustomer(), previousOrder.getItemsWithAmount());
        createOrder(newOrder);
        return newOrder;
    }

    public Collection<Order> getActiveOrders(){
        return orderDatabase.values().stream()
                .filter(order -> order.getOrderState().equals(IN_PROGRESS))
                .collect(Collectors.toList());
    }

    public Collection<Order> getDeliveredOrders(){
        return orderDatabase.values().stream()
                .filter(order -> order.getOrderState().equals(DELIVERED))
                .collect(Collectors.toList());
    }

    public Order getOrderById(String searchedId){
        Order order =  orderDatabase.get(searchedId);
        if (order == null){
            throw new OrderNotFoundException("Id");
        } return order;
    }



    public static <T> void checkIfInputNull(T input) {
        if (input == null) {
            throw new InputCanNotBeNullException();
        }
    }

    private void createDefaultData(){
        for (Order order:defaultData.getDefaultOrders()){
            this.createOrder(order);
        }
    }
}
