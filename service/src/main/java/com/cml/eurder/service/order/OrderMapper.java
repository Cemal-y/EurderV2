package com.cml.eurder.service.order;

import com.cml.eurder.domain.item.ItemRepository;
import com.cml.eurder.domain.order.Order;
import com.cml.eurder.domain.order.OrderItem;
import com.cml.eurder.domain.user.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    ItemRepository itemRepository;
    CustomerRepository customerRepository;

    @Autowired
    public OrderMapper(ItemRepository itemRepository, CustomerRepository customerRepository) {
        this.itemRepository = itemRepository;
        this.customerRepository = customerRepository;
    }

    public Collection<OrderDto> toDto(Collection<Order> orderCollection) {
        return orderCollection.stream().map(this::toDto).collect(Collectors.toList());
    }

    public OrderDto toDto(Order order) {
        return new OrderDto(order.getID(), order.getCustomer(), order.getItemsWithAmount()
                , order.getTotalPrice(), order.getOrderState());
    }

    public Order toOrder(CreateOrderDto createOrderDto) {
        return new Order(customerRepository.findById(createOrderDto.getCustomerId()).get()
                , createOrderDto.getItems().keySet()
                .stream().map(id -> new OrderItem(itemRepository.findById(Long.parseLong(id)).get(), createOrderDto.getItems().get(id)))
                .collect(Collectors.toList())
        );
    }


//    public Order toOrder(CreateOrderDto createOrderDto) {
//        return new Order(customerRepository.getCustomerById(createOrderDto.getCustomerId())
//                , createOrderDto.getOrderItems()
//                .stream().map(orderItem ->
//                        new OrderItem(itemRepository.getItemById(orderItem.getItem().getId())
//                                , orderItem.getItemAmount()))
//                .collect(Collectors.toList())
//        );
//    }
}
