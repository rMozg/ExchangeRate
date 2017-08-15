package ru.mozgovoy.oleg.exchangerate.ui.presenter;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.os.ResultReceiver;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import ru.mozgovoy.oleg.exchangerate.R;
import ru.mozgovoy.oleg.exchangerate.model.core.Currency;
import ru.mozgovoy.oleg.exchangerate.model.core.CurrencyRate;
import ru.mozgovoy.oleg.exchangerate.service.DownloadService;
import ru.mozgovoy.oleg.exchangerate.ui.view.IRateView;

public class RatePresenter implements IRatePresenter {

    private static final int MATH_SCALE = 6;

    private final Currency alwaysInListCurrency;
    private final CurrencyRate alwaysInListCurrencyRate;
    private final IRateView rateView;

    private Map<Currency, CurrencyRate> currencyRates = new HashMap<>();

    public RatePresenter(@NonNull Resources resources, @NonNull IRateView rateView) {
        alwaysInListCurrency = new Currency(643, "RUB", resources.getString(R.string.rub_name));
        alwaysInListCurrencyRate = new CurrencyRate(alwaysInListCurrency, 1, BigDecimal.ONE);
        this.rateView = rateView;
    }

    @Override
    public void startDownloadNewRates(Context appContext) {
        DownloadService.startDownload(appContext);
        ResultReceiver resultReceiver = new ResultReceiver(new Handler()) {
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                super.onReceiveResult(resultCode, resultData);
            }
        };
        
    }

    @Override
    public void startRecalculate(@NonNull BigDecimal value, @NonNull Currency currencyFrom, @NonNull Currency currencyTo) {
        CurrencyRate currencyRateFrom = currencyRates.get(currencyFrom);
        CurrencyRate currencyRateTo = currencyRates.get(currencyTo);
        if (currencyRateFrom == null || currencyRateTo == null) {
            rateView.showError(IRateView.ErrorType.CALCULATE);
            return;
        }
        BigDecimal res = value
                .multiply(currencyRateFrom.getValue())
                .multiply(new BigDecimal(currencyRateTo.getNominal()))
                .divide(new BigDecimal(currencyRateFrom.getNominal())
                                .multiply(currencyRateTo.getValue()),
                        MATH_SCALE,
                        BigDecimal.ROUND_HALF_UP);
        rateView.showRecalculatedValue(res);
    }
}
