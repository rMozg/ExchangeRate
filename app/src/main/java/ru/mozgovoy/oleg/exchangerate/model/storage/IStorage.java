package ru.mozgovoy.oleg.exchangerate.model.storage;

import android.support.annotation.NonNull;

import java.util.List;

import ru.mozgovoy.oleg.exchangerate.model.core.CurrencyRate;

public interface IStorage {

    void setCurrencyRates(@NonNull List<CurrencyRate> currencyRates);

    @NonNull
    List<CurrencyRate> getCurrencyRates();
}
