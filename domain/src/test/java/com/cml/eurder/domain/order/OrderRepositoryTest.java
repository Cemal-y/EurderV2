package com.cml.eurder.domain.order;

import com.cml.eurder.domain.DefaultData;
import com.cml.eurder.domain.exceptions.OrderNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderRepositoryTest {
    OrderRepository orderRepository = new OrderRepository(new DefaultData());

//    @Test
//    public void reorderTest(){
//        Order order = orderRepository.getActiveOrders().stream().findFirst().orElse(null);
//        orderRepository.getActiveOrders().
//    }

}