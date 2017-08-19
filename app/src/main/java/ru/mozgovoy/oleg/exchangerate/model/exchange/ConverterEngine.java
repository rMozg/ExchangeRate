package ru.mozgovoy.oleg.exchangerate.model.exchange;


import android.support.annotation.Nullable;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ru.mozgovoy.oleg.exchangerate.model.core.Currency;
import ru.mozgovoy.oleg.exchangerate.model.core.CurrencyRate;

public class ConverterEngine implements IConverterEngine {

    @Override
    @Nullable
    public List<CurrencyRate> fromXml(@Nullable String xml) {
        if (xml == null) {
            return null;
        }
        StringReader reader = new StringReader(xml);
        Serializer serializer = new Persister();
        try {
            ValCurs valCurs = serializer.read(ValCurs.class, reader, false);
            if (valCurs != null) {
                List<Valute> valutes = valCurs.getValuteList();
                if (valutes != null) {
                    List<CurrencyRate> currencyRates = new ArrayList<>();
                    for (Valute valute : valutes) {
                        currencyRates.add(
                                new CurrencyRate(
                                        new Currency(
                                                valute.getNumCode(),
                                                valute.getCharCode(),
                                                valute.getName()
                                        ),
                                        valute.getNominal(),
                                        new BigDecimal(valute.getValue().replace(",", "."))
                                )
                        );
                    }
                    return currencyRates;
                }
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return null;
    }
}
