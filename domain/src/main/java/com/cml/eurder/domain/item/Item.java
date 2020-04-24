package com.cml.eurder.domain.item;

import java.time.LocalDate;
import java.util.UUID;

public class Item implements Orderable {
    private String id;
    private String name;
    private String description;
    private int stockAmount;
    private Price price;
    private boolean isInStock;
    private LocalDate shippingDate;

    public Item() {
//        setShippingDate();
    }

    public Item(ItemBuilder itemBuilder) {
        this.id = UUID.randomUUID().toString();
        this.name = itemBuilder.name;
        this.description = itemBuilder.description;
        this.stockAmount = itemBuilder.stockAmount;
        this.price = itemBuilder.price;
        setShippingDate();
    }

    public void setShippingDate() {
        if(stockAmount == 0){
            shippingDate = LocalDate.now().plusWeeks(1);
        } else {
            shippingDate = LocalDate.now().plusDays(1);
        }

    }

    public void deductFromStockAmount(int orderAmount) {
        if (stockAmount > 0 && stockAmount > orderAmount){
            stockAmount = stockAmount - orderAmount;
        } else {
            stockAmount = 0;
        }
        setItemToAvailableIfStockISEnough();
        setShippingDate();
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public Item setDescription(String description) {
        this.description = description;
        return this;
    }


    public Item setPrice(Price price) {
        this.price = price;
        return this;
    }

    public Item setStockAmount(int stockAmount) {
        this.stockAmount = stockAmount;
        return this;
    }

    private void setItemToAvailableIfStockISEnough() {
        if(stockAmount > 0){
            this.isInStock = true;
        } else {
            this.isInStock = false;
        }
    }


    public boolean isInStock() {
        return isInStock;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Price getPrice() {
        return price;
    }

    @Override
    public int getStockAmount() {
        return stockAmount;
    }

    @Override
    public String getId() {
        return id;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public static class ItemBuilder {
        private String name;
        private String description;
        private int stockAmount;
        private Price price;

        private ItemBuilder() {
        }

        public static ItemBuilder itemBuilder() {
            return new ItemBuilder();
        }
        public Item build() {
            return new Item(this);
        }

        public ItemBuilder withName(String name) {
            this.name = name;
            return this;
        }
        public ItemBuilder withDescription(String description) {
            this.description = description;
            return this;
        }
        public ItemBuilder withStockAmount(int stockAmount) {
            this.stockAmount = stockAmount;
            return this;
        }
        public ItemBuilder withPrice(Price price) {
            this.price = price;
            return this;
        }
    }
}
