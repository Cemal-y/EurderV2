package com.cml.eurder.domain.item;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "items")
public class Item implements Orderable {
    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "stock_amount")
    private int stockAmount;
    @Column(name = "price")
    private long price;
    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private Currencies currency;
    @Column(name = "stock_status")
    private boolean isInStock;
    @Column(name = "shipping_date")
    private LocalDate shippingDate;

    public Item() {
//        setShippingDate();
    }

    public Item(String name, String description, int stockAmount, long price
            , Currencies currency) {
        this.name = name;
        this.description = description;
        this.stockAmount = stockAmount;
        this.price = price;
        this.currency = currency;
        setShippingDate();
        setItemToAvailableIfStockISEnough();
    }

    public Item(long id, String name, String description, int stockAmount, long price
            , Currencies currency) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stockAmount = stockAmount;
        this.price = price;
        this.currency = currency;
        setShippingDate();
        setItemToAvailableIfStockISEnough();
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

    public Currencies getCurrency() {
        return currency;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
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

    public Item setDescription(String description) {
        this.description = description;
        return this;
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
    public int getStockAmount() {
        return stockAmount;
    }

    @Override
    public long getId() {
        return id;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

}
