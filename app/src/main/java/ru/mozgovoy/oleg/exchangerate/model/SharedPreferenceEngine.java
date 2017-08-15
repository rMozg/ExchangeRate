package ru.mozgovoy.oleg.exchangerate.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ru.mozgovoy.oleg.exchangerate.model.core.CurrencyRate;

public class SharedPreferenceEngine {

    private static final String APP_PREFERENCES = "APP_PREFERENCES";
    private static final String PREFERENCE_NAME_CURRENT_RATES = "PREFERENCE_NAME_CURRENT_RATES";

    public static SharedPreferences preferences;

    public static void initPreferences(Context context) {
        preferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static void setCurrencyRates(@NonNull List<CurrencyRate> currencyRates) {
        SharedPreferences.Editor editor = preferences.edit();
        Set<String> rates = new HashSet<>();

        Serializer serializer = new Persister();
        for (int i = 0; i < currencyRates.size(); i++) {
            StringWriter stringWriter = new StringWriter();
            try {
                serializer.write(currencyRates.get(i), stringWriter);
            } catch (Exception exc) {
                exc.printStackTrace();
            }
            rates.add(stringWriter.toString());
        }

        editor.remove(PREFERENCE_NAME_CURRENT_RATES);
        editor.putStringSet(PREFERENCE_NAME_CURRENT_RATES, rates);
        editor.commit();
    }

    @NonNull
    public static List<CurrencyRate> getCurrencyRates() {
        List<String> strings = new ArrayList<>(preferences.getStringSet(PREFERENCE_NAME_CURRENT_RATES, new HashSet<String>()));
        List<CurrencyRate> currencyRates = new ArrayList<>();
        Serializer serializer = new Persister();
        for (int i = 0; i < strings.size(); i++) {
            StringReader stringReader = new StringReader(strings.get(i));
            try {
                CurrencyRate currencyRate = serializer.read(CurrencyRate.class, stringReader);
                if (currencyRate != null) {
                    currencyRates.add(currencyRate);
                }
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        return currencyRates;
    }

}

