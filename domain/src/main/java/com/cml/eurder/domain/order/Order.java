package com.cml.eurder.domain.order;

import com.cml.eurder.domain.user.User;

import java.util.List;
import java.util.UUID;

import static com.cml.eurder.domain.order.OrderState.DELIVERED;
import static com.cml.eurder.domain.order.OrderState.IN_PROGRESS;

public class Order {
    private final String ID;
    private User customer;
    private List<OrderItem> itemsWithAmount;
    private double totalPrice;
    private OrderState orderState;

    public Order(User customer, List<OrderItem> itemsToAdd) {
        this.ID = UUID.randomUUID().toString();
        this.itemsWithAmount = itemsToAdd;
        this.customer = customer;
        this.orderState = IN_PROGRESS;
        this.totalPrice = calculateTotalPrice();
    }

    public double calculateTotalPrice(){
        return itemsWithAmount.stream()
                .mapToDouble(orderItem -> orderItem.getItem().getPrice().getPriceAmount()
                        * orderItem.getItemAmount())
                .sum();
    }


//    public void addItemToOrder(Item item, int amount){
//        item.setItemAmount(amount);
//        item.deductFromStockAmount(amount);
//        items.add(item);
//    }



    public void setOrderAsDelivered(){
        orderState = DELIVERED;
    }

    public java.lang.String getID() {
        return ID;
    }

    public User getCustomer() {
        return customer;
    }

    public List<OrderItem> getItemsWithAmount() {
        return itemsWithAmount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public OrderState getOrderState() {
        return orderState;
    }




}
