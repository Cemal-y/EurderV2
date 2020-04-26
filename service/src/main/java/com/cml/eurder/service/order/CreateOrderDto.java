package com.cml.eurder.service.order;

import java.util.Map;

public class CreateOrderDto {
    private long customerId;
    private Map<String, Integer> items;

    public CreateOrderDto() {
    }
    public CreateOrderDto(long customerId,  Map<String, Integer> items) {
        this.customerId = customerId;
        this.items = items;
    }

    public long getCustomerId() {
        return customerId;
    }

    public Map<String, Integer> getItems() {
        return items;
    }
}
