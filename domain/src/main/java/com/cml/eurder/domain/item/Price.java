package com.cml.eurder.domain.item;

public class Price {
    private double priceAmount;
    private Currencies currencies;

    public Price() {
    }

    public Price(double priceAmount, Currencies currencies) {
        this.priceAmount = priceAmount;
        this.currencies = currencies;
    }

    public double getPriceAmount() {
        return priceAmount;
    }

    public Currencies getCurrencies() {
        return currencies;
    }
}
