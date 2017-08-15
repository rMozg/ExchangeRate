package ru.mozgovoy.oleg.exchangerate.model;

import android.app.Application;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferenceEngine.initPreferences(getApplicationContext());
    }
}
