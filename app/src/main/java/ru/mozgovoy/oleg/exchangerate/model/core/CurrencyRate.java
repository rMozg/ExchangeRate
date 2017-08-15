package ru.mozgovoy.oleg.exchangerate.model.core;

import android.support.annotation.NonNull;

import org.simpleframework.xml.Default;

import java.math.BigDecimal;

@Default
public class CurrencyRate {
    @NonNull
    private Currency currency;
    private int nominal;
    @NonNull
    private BigDecimal value;

    public CurrencyRate(@NonNull Currency currency, int nominal, @NonNull BigDecimal value) {
        this.currency = currency;
        this.nominal = nominal;
        this.value = value;
    }

    @NonNull
    public Currency getCurrency() {
        return currency;
    }

    public int getNominal() {
        return nominal;
    }

    @NonNull
    public BigDecimal getValue() {
        return value;
    }
}
