package ru.mozgovoy.oleg.exchangerate.model;

import android.app.Application;

import ru.mozgovoy.oleg.exchangerate.model.storage.IStorage;
import ru.mozgovoy.oleg.exchangerate.model.storage.SharedPreferenceEngine;

public class MyApplication extends Application {

    private static MyApplication myApplication;
    private static IStorage storage;

    public static MyApplication getInstance() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        storage = new SharedPreferenceEngine(getApplicationContext());
    }

    public IStorage getStorage() {
        return storage;
    }
}
