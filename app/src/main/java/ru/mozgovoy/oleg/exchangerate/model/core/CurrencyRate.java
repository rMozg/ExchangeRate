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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrencyRate)) return false;

        CurrencyRate that = (CurrencyRate) o;

        if (nominal != that.nominal) return false;
        if (!currency.equals(that.currency)) return false;
        return value.equals(that.value);

    }

    @Override
    public int hashCode() {
        int result = currency.hashCode();
        result = 31 * result + nominal;
        result = 31 * result + value.hashCode();
        return result;
    }
}
