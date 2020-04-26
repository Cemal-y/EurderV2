package com.cml.eurder.service.item;

import com.cml.eurder.domain.item.Currencies;
import com.cml.eurder.domain.item.Currency;

import java.time.LocalDate;
import java.util.Objects;

//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemDto {
    private long id;
    private String name;
    private String description;
    private int stockAmount;
    private long price;
    private Currencies currency;
    private LocalDate shippingDate;
    private boolean isInStock;

    public ItemDto() {
    }

    public ItemDto(long id, String name, String description, int stockAmount
            , long price, Currencies currency, LocalDate shippingDate, boolean isInStock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stockAmount = stockAmount;
        this.price = price;
        this.currency = currency;
        this.shippingDate = shippingDate;
        this.isInStock = isInStock;
    }

    public long getId() {
        return id;
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

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public boolean isInStock() {
        return isInStock;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemDto)) return false;
        ItemDto itemDto = (ItemDto) o;
        return getId() == itemDto.getId() &&
                getStockAmount() == itemDto.getStockAmount() &&
                getPrice() == itemDto.getPrice() &&
                isInStock() == itemDto.isInStock() &&
                Objects.equals(getName(), itemDto.getName()) &&
                Objects.equals(getDescription(), itemDto.getDescription()) &&
                getCurrency() == itemDto.getCurrency() &&
                Objects.equals(getShippingDate(), itemDto.getShippingDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getStockAmount(), getPrice(), getCurrency(), getShippingDate(), isInStock());
    }

    @Override
    public String toString() {
        return "ItemDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", stockAmount=" + stockAmount +
                ", price=" + price +
                ", currency=" + currency +
                ", shippingDate=" + shippingDate +
                ", isInStock=" + isInStock +
                '}';
    }
}
