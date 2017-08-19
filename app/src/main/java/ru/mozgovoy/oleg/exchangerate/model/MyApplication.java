package ru.mozgovoy.oleg.exchangerate.model;

import android.app.Application;

import ru.mozgovoy.oleg.exchangerate.model.exchange.ConverterEngine;
import ru.mozgovoy.oleg.exchangerate.model.exchange.IConverterEngine;
import ru.mozgovoy.oleg.exchangerate.model.network.INetworkHelper;
import ru.mozgovoy.oleg.exchangerate.model.network.NetworkHelper;
import ru.mozgovoy.oleg.exchangerate.model.storage.IStorage;
import ru.mozgovoy.oleg.exchangerate.model.storage.SharedPreferenceEngine;

public class MyApplication extends Application {

    private static MyApplication myApplication;
    private IStorage storage;
    private IConverterEngine converterEngine;
    private INetworkHelper networkHelper;

    public static MyApplication getInstance() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        storage = new SharedPreferenceEngine(getApplicationContext());
        converterEngine = new ConverterEngine();
        networkHelper = new NetworkHelper();
    }

    public IStorage getStorage() {
        return storage;
    }

    public IConverterEngine getConverterEngine() {
        return converterEngine;
    }

    public INetworkHelper getNetworkHelper() {
        return networkHelper;
    }
}
