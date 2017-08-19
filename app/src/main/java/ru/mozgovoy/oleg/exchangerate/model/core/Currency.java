package ru.mozgovoy.oleg.exchangerate.model.core;

import android.support.annotation.NonNull;

import org.simpleframework.xml.Default;

@Default
public class Currency {
    private int code;
    @NonNull
    private String shortName;
    @NonNull
    private String name;

    public Currency(int code, String shortName, String name) {
        this.code = code;
        this.shortName = shortName;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getShortName() {
        return shortName;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Currency)) return false;

        Currency currency = (Currency) o;

        if (code != currency.code) return false;
        if (!shortName.equals(currency.shortName)) return false;
        return name.equals(currency.name);

    }

    @Override
    public int hashCode() {
        int result = code;
        result = 31 * result + shortName.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
