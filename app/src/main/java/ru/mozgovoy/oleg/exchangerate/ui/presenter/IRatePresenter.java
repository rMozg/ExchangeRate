package ru.mozgovoy.oleg.exchangerate.ui.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import java.math.BigDecimal;

import ru.mozgovoy.oleg.exchangerate.model.core.Currency;

public interface IRatePresenter {

    void startDownloadNewRates(Context appContext);

    void recalculateButtonClicked(@NonNull BigDecimal value, @NonNull Currency currencyFrom, @NonNull Currency currencyTo);
}
