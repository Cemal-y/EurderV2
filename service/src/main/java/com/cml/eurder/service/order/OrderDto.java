package com.cml.eurder.service.order;

import com.cml.eurder.domain.order.OrderItem;
import com.cml.eurder.domain.order.OrderState;
import com.cml.eurder.domain.user.User;

import java.util.List;

public class OrderDto {
    private long id;
    private User customer;
    private List<OrderItem> items;
    private double totalPrice;
    private OrderState orderState;

    public OrderDto() {
    }
    public OrderDto(long id, User customer, List<OrderItem> items, double totalPrice, OrderState orderState) {
        this.id = id;
        this.customer = customer;
        this.items = items;
        this.totalPrice = totalPrice;
        this.orderState = orderState;
    }

    public long getId() {
        return id;
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
