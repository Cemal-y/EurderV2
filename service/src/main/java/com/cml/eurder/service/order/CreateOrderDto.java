package com.cml.eurder.service.order;

import java.util.Map;

public class CreateOrderDto {
    private long customerId;
    private Map<Long, Integer> items;

    public CreateOrderDto() {
    }
    public CreateOrderDto(long customerId,  Map<Long, Integer> items) {
        this.customerId = customerId;
        this.items = items;
    }

    public long getCustomerId() {
        return customerId;
    }

    public Map<Long, Integer> getItems() {
        return items;
    }
}
