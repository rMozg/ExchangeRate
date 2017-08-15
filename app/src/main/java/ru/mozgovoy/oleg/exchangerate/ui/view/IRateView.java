package ru.mozgovoy.oleg.exchangerate.ui.view;

import java.math.BigDecimal;
import java.util.List;

import ru.mozgovoy.oleg.exchangerate.model.core.Currency;

public interface IRateView {

    void showCurrency(List<Currency> currencies);

    void showRecalculatedValue(BigDecimal value);

    void showError(ErrorType errorType);

    public static enum ErrorType {DOWNLOAD, CALCULATE}
}
