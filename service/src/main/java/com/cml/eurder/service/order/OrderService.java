package com.cml.eurder.service.order;

import com.cml.eurder.domain.item.ItemRepository;
import com.cml.eurder.domain.order.OrderRepository;
import com.cml.eurder.domain.user.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private OrderMapper orderMapper;
    private ItemRepository itemRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper
            , ItemRepository itemRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.itemRepository = itemRepository;
        this.customerRepository = customerRepository;
    }

    public Collection<OrderDto> getAllActiveOrders() {
        return orderMapper.toDto(orderRepository.getActiveOrders());
    }

    public Collection<OrderDto> getDeliveredOrders() {
        return orderMapper.toDto(orderRepository.getActiveOrders());
    }

    public OrderDto addOrder(CreateOrderDto createOrderDto){
        return orderMapper.toDto(orderRepository.createOrder(orderMapper.toOrder(createOrderDto)));
    }

    public OrderDto reorderAPreviousOrder(String orderId){
        return orderMapper.toDto(orderRepository.reorderAPreviousOrder(orderId));
    }

    public Collection<OrderDto> getOrdersOfACustomer(long customerId) {
        return orderMapper.toDto(orderRepository.getOrdersOfACustomer(customerId));
    }


}
