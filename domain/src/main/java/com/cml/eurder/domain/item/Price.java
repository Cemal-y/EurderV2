package com.cml.eurder.domain.item;

public class Price {
    private double priceAmount;
    private Currency currency;

    public Price() {
    }

    public Price(double priceAmount, Currency currency) {
        this.priceAmount = priceAmount;
        this.currency = currency;
    }

    public double getPriceAmount() {
        return priceAmount;
    }

    public Currency getCurrency() {
        return currency;
    }
}
