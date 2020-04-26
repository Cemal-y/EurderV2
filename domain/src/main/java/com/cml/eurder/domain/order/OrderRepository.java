package com.cml.eurder.domain.order;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    @Override
    <S extends Order> S save(S s);

    @Override
    Optional<Order> findById(Long aLong);

    Collection<Order> findAllByCustomer_Id(long customerId);

    Collection<Order> findAllByOrderState(OrderState orderState);

//    public Order reorderAPreviousOrder(String orderId){
//        Order previousOrder =  getOrderById(orderId);
//        Order newOrder = new Order(previousOrder.getCustomer(), previousOrder.getItemsWithAmount());
//        createOrder(newOrder);
//        return newOrder;
//    }

}
