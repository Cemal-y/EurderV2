package com.cml.eurder.domain.order;

import com.cml.eurder.domain.exceptions.InputCanNotBeNullException;
import com.cml.eurder.domain.exceptions.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static com.cml.eurder.domain.order.OrderState.DELIVERED;
import static com.cml.eurder.domain.order.OrderState.IN_PROGRESS;

@Repository
public class OrderRepository {
    ConcurrentHashMap<String, Order> orderDatabase;

//    DefaultData defaultData;

//    @Autowired
//    public OrderRepository(DefaultData defaultData) {
//        this.orderDatabase = new ConcurrentHashMap<>();
//        this.defaultData = defaultData;
//        createDefaultData();
//    }

    @Autowired
    public OrderRepository() {
        this.orderDatabase = new ConcurrentHashMap<>();
//        createDefaultData();
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

    public Collection<Order> getOrdersOfACustomer(long customerId){
       return orderDatabase.values().stream()
                .filter(order -> order.getCustomer().getId() == customerId)
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

//    private void createDefaultData(){
//        for (Order order:defaultData.getDefaultOrders()){
//            this.createOrder(order);
//        }
//    }
}
