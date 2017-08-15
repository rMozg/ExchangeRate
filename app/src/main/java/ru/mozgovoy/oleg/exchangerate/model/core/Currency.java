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
}
