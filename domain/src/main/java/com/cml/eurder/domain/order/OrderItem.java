package com.cml.eurder.domain.order;

import com.cml.eurder.domain.item.Item;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private Item item;
    @Column(name = "item_amount")
    private int itemAmount;

    public OrderItem() {
    }

    public OrderItem(Item item, int itemAmount) {
        this.item = item;
        this.itemAmount = itemAmount;
    }

    public Item getItem() {
        return item;
    }

    public int getItemAmount() {
        return itemAmount;
    }


    public void setItemAmount(int itemAmount) {
        this.itemAmount = itemAmount;
    }
}
