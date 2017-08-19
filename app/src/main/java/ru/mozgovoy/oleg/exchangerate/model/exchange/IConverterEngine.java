package ru.mozgovoy.oleg.exchangerate.model.exchange;

import android.support.annotation.Nullable;

import java.util.List;

import ru.mozgovoy.oleg.exchangerate.model.core.CurrencyRate;

public interface IConverterEngine {
    @Nullable
    List<CurrencyRate> fromXml(String xml);
}
