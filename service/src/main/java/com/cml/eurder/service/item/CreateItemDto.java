package com.cml.eurder.service.item;

import com.cml.eurder.domain.item.Currencies;

import java.time.LocalDate;

public class CreateItemDto {
    private String name;
    private String description;
    private int stockAmount;
    private long price;
    private Currencies currency;

    public CreateItemDto() {
    }

    public CreateItemDto(String name, String description, int stockAmount
            , long price, Currencies currency) {
        this.name = name;
        this.description = description;
        this.stockAmount = stockAmount;
        this.price = price;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public long getPrice() {
        return price;
    }

    public Currencies getCurrency() {
        return currency;
    }
}
