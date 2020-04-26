package com.cml.eurder.domain.item;

public interface Orderable {
    String getName();
    String getDescription();
    long getPrice();
    int getStockAmount();
    long getId();
}
