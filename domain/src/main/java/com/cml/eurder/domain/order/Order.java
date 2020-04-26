package com.cml.eurder.domain.order;

import com.cml.eurder.domain.user.Customer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.cml.eurder.domain.order.OrderState.DELIVERED;
import static com.cml.eurder.domain.order.OrderState.IN_PROGRESS;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<OrderItem> itemsWithAmount = new ArrayList<>();
    @Column(name = "total_price")
    private double totalPrice;
    @Column(name = "order_state")
    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    public Order() {
    }

    public Order(Customer customer, List<OrderItem> itemsToAdd) {
        this.itemsWithAmount = itemsToAdd;
        this.customer = customer;
        this.orderState = IN_PROGRESS;
        this.totalPrice = calculateTotalPrice();
    }

    public double calculateTotalPrice(){
        return itemsWithAmount.stream()
                .mapToDouble(orderItem -> orderItem.getItem().getPrice()
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

    public long getId() {
        return id;
    }

    public Customer getCustomer() {
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
