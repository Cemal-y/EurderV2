package com.cml.eurder.domain.item;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.*;

@Entity
@JsonAutoDetect
@Table(name = "currency")
public class Currency {
    @Id
    @Column(name = "currency_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long currencyId;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private Currencies currencies;

    public Currency() {
    }

    public Currency(Currencies currencies) {
        this.currencies = currencies;
    }

    public long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(long currencyId) {
        this.currencyId = currencyId;
    }

    public Currencies getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Currencies currencies) {
        this.currencies = currencies;
    }
}
