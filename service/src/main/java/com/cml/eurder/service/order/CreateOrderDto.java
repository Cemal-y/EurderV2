package com.cml.eurder.service.order;

import com.cml.eurder.domain.item.Item;
import com.cml.eurder.domain.order.OrderItem;
import com.cml.eurder.domain.order.OrderState;
import com.cml.eurder.domain.user.Customer;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class CreateOrderDto {
    private String customerId;
    private Map<String, Integer> items;

    public CreateOrderDto() {
    }
    public CreateOrderDto(String customerId,  Map<String, Integer> items) {
        this.customerId = customerId;
        this.items = items;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Map<String, Integer> getItems() {
        return items;
    }
}
