package ru.mozgovoy.oleg.exchangerate.model.math;

import android.support.annotation.NonNull;

import java.math.BigDecimal;

import ru.mozgovoy.oleg.exchangerate.model.core.CurrencyRate;


public interface IMathEngine {
    @NonNull
    BigDecimal convertCurrency(@NonNull BigDecimal value, @NonNull CurrencyRate currencyRateFrom, @NonNull CurrencyRate currencyRateTo);
}
