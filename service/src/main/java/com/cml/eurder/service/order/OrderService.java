package com.cml.eurder.service.order;

import com.cml.eurder.domain.exceptions.InputCanNotBeNullException;
import com.cml.eurder.domain.exceptions.OrderDoesNotBelongToCustomerException;
import com.cml.eurder.domain.exceptions.OrderNotFoundException;
import com.cml.eurder.domain.item.ItemRepository;
import com.cml.eurder.domain.order.OrderRepository;
import com.cml.eurder.service.DefaultData;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.cml.eurder.domain.order.OrderState.DELIVERED;
import static com.cml.eurder.domain.order.OrderState.IN_PROGRESS;

@Service
@Transactional
public class OrderService {
    private OrderRepository orderRepository;
    private OrderMapper orderMapper;
    private ItemRepository itemRepository;
    private DefaultData defaultData;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper,
                        ItemRepository itemRepository, DefaultData defaultData) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.itemRepository = itemRepository;
        this.defaultData = defaultData;
//        createDefaultData();
    }

    public Collection<OrderDto> getAllActiveOrders() {
        return orderMapper.toDto(orderRepository.findAllByOrderState(IN_PROGRESS));
    }

    public Collection<OrderDto> getDeliveredOrders() {
        return orderMapper.toDto(orderRepository.findAllByOrderState(DELIVERED));
    }

    public OrderDto addOrder(CreateOrderDto createOrderDto){
        checkIfInputIsNull(createOrderDto);
        decreaseStockAmounts(createOrderDto);
        return orderMapper.toDto(orderRepository.save(orderMapper.toOrder(createOrderDto)));
    }

    public OrderDto reorder(long orderId, long customerId){
        checkIfOrderBelongsToTheCustomer(orderId, customerId);
        OrderDto orderDto = getOrderById(orderId);
        HashMap<Long, Integer> orderItems = new HashMap<>();
        orderDto.getItems().stream().forEach(orderItem -> orderItems.put(orderItem.getItem().getId(),
                orderItem.getItemAmount()));
        return addOrder(new CreateOrderDto(customerId, orderItems));
    }


    public Collection<OrderDto> getOrdersOfACustomer(long customerId) {
        return orderMapper.toDto(orderRepository.findAllByCustomer_Id(customerId));
    }

    public OrderDto getOrderById(long orderId) {
        return orderMapper.toDto(orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException("Id")));
    }


    private void decreaseStockAmounts(CreateOrderDto createOrderDto) {
        createOrderDto.getItems().keySet()
                .forEach(itemID -> itemRepository.findById(itemID)
                        .get().deductFromStockAmount(createOrderDto.getItems().get(itemID)));
    }

    private static <T> void checkIfInputIsNull(T input) {
        if (input == null) {
            throw new InputCanNotBeNullException();
        }
    }

    private void checkIfOrderBelongsToTheCustomer(long orderId, long customerId) {
        if (getOrderById(orderId).getCustomer().getId() != customerId) {
            throw new OrderDoesNotBelongToCustomerException(orderId);
        }
    }

    private void createDefaultData(){
        for (CreateOrderDto createOrderDto:defaultData.getDefaultOrders()){
            this.addOrder(createOrderDto);
        }
    }

}
