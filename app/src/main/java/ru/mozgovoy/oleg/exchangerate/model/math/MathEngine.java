package ru.mozgovoy.oleg.exchangerate.model.math;

import android.support.annotation.NonNull;

import java.math.BigDecimal;

import ru.mozgovoy.oleg.exchangerate.model.core.CurrencyRate;

public class MathEngine implements IMathEngine {

    private static final int MATH_SCALE = 10;

    @Override
    @NonNull
    public BigDecimal convertCurrency(@NonNull BigDecimal value, @NonNull CurrencyRate currencyRateFrom, @NonNull CurrencyRate currencyRateTo) {
        return value
                .multiply(currencyRateFrom.getValue())
                .multiply(new BigDecimal(currencyRateTo.getNominal()))
                .divide(new BigDecimal(currencyRateFrom.getNominal())
                                .multiply(currencyRateTo.getValue()),
                        MATH_SCALE,
                        BigDecimal.ROUND_HALF_UP);
    }
}
