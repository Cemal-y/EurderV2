package com.cml.eurder.service.order;

import com.cml.eurder.domain.item.Item;
import com.cml.eurder.domain.order.OrderItem;
import com.cml.eurder.domain.order.OrderState;
import com.cml.eurder.domain.user.Customer;
import com.cml.eurder.domain.user.User;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class OrderDto {
    private String ID;
    private User customer;
    private List<OrderItem> items;
    private double totalPrice;
    private OrderState orderState;

    public OrderDto() {
    }
    public OrderDto(String ID, User customer, List<OrderItem> items, double totalPrice, OrderState orderState) {
        this.ID = ID;
        this.customer = customer;
        this.items = items;
        this.totalPrice = totalPrice;
        this.orderState = orderState;
    }

    public String getID() {
        return ID;
    }

    public User getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public OrderState getOrderState() {
        return orderState;
    }
}
