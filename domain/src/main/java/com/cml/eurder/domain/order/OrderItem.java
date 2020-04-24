package com.cml.eurder.domain.order;

import com.cml.eurder.domain.item.Item;

public class OrderItem {
    private Item item;
    private int itemAmount;


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
