package ru.mozgovoy.oleg.exchangerate.ui.presenter;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.os.ResultReceiver;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ru.mozgovoy.oleg.exchangerate.R;
import ru.mozgovoy.oleg.exchangerate.model.core.Currency;
import ru.mozgovoy.oleg.exchangerate.model.core.CurrencyRate;
import ru.mozgovoy.oleg.exchangerate.model.exchange.IConverterEngine;
import ru.mozgovoy.oleg.exchangerate.model.math.IMathEngine;
import ru.mozgovoy.oleg.exchangerate.model.storage.IStorage;
import ru.mozgovoy.oleg.exchangerate.service.DownloadService;
import ru.mozgovoy.oleg.exchangerate.ui.view.IRateView;

public class RatePresenter implements IRatePresenter {

    private final CurrencyRate alwaysInListCurrencyRate;
    private final IRateView rateView;
    private final IStorage storage;
    private final IConverterEngine converterEngine;
    private final IMathEngine mathEngine;
    private final Map<Currency, CurrencyRate> currencyRates = new ConcurrentHashMap<>();


    public RatePresenter(
            @NonNull Resources resources,
            @NonNull IRateView rateView,
            @NonNull IStorage storage,
            @NonNull IConverterEngine converterEngine,
            @NonNull IMathEngine mathEngine
    ) {
        alwaysInListCurrencyRate = new CurrencyRate(
                new Currency(
                        643,
                        "RUB",
                        resources.getString(R.string.rub_name)
                ), 1, BigDecimal.ONE);
        this.rateView = rateView;
        this.storage = storage;
        this.converterEngine = converterEngine;
        this.mathEngine = mathEngine;
        refreshCurrencyRatesMap(storage.getCurrencyRates());
        rateView.setCurrency(new ArrayList<Currency>(currencyRates.keySet()));
    }

    @Override
    public void startDownloadNewRates(Context appContext) {
        ResultReceiver resultReceiver = new DownloadResultReceiver(new Handler()) {
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                if (resultCode == RESULT_OK) {
                    String xml = resultData.getString(RESULT_OK_PARAM_XML_TEXT);
                    List<CurrencyRate> currencyRatesList = converterEngine.fromXml(xml);
                    if (currencyRatesList != null) {
                        refreshCurrencyRatesMap(currencyRatesList);
                        storage.setCurrencyRates(currencyRatesList);
                        rateView.setCurrency(new ArrayList<Currency>(currencyRates.keySet()));
                    } else {
                        rateView.showError(IRateView.ErrorType.DOWNLOAD);
                        rateView.setCurrency(new ArrayList<Currency>(currencyRates.keySet()));
                    }
                } else if (resultCode == RESULT_ERROR) {
                    rateView.showError(IRateView.ErrorType.DOWNLOAD);
                    rateView.setCurrency(new ArrayList<Currency>(currencyRates.keySet()));
                }
                super.onReceiveResult(resultCode, resultData);
            }
        };
        DownloadService.startDownload(appContext, resultReceiver);
    }

    @Override
    public void recalculateButtonClicked(@NonNull BigDecimal value, @NonNull Currency currencyFrom, @NonNull Currency currencyTo) {
        CurrencyRate currencyRateFrom = currencyRates.get(currencyFrom);
        CurrencyRate currencyRateTo = currencyRates.get(currencyTo);
        if (currencyRateFrom == null || currencyRateTo == null) {
            rateView.showError(IRateView.ErrorType.CALCULATE);
            return;
        }

        BigDecimal res = mathEngine.convertCurrency(value, currencyRateFrom, currencyRateTo);
        rateView.showRecalculatedValue(res);
    }

    private void refreshCurrencyRatesMap(List<CurrencyRate> currencyRatesList) {
        currencyRates.clear();
        currencyRates.put(alwaysInListCurrencyRate.getCurrency(), alwaysInListCurrencyRate);
        for (CurrencyRate currencyRate : currencyRatesList) {
            currencyRates.put(currencyRate.getCurrency(), currencyRate);
        }
    }

    public static abstract class DownloadResultReceiver extends ResultReceiver {
        public static final int RESULT_OK = 1;
        public static final String RESULT_OK_PARAM_XML_TEXT = "RESULT_OK_PARAM_XML_TEXT";
        public static final int RESULT_ERROR = 2;

        public DownloadResultReceiver(Handler handler) {
            super(handler);
        }
    }
}
