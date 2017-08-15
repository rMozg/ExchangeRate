package ru.mozgovoy.oleg.exchangerate.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import ru.mozgovoy.oleg.exchangerate.model.NetworkUtils;

public class DownloadService extends IntentService {

    private static final String ACTION_START_DOWNLOAD = "ru.mozgovoy.oleg.exchangerate.service.action.ACTION_START_DOWNLOAD";

    public static String CURRENCY_RATE_ADDRESS = "http://www.cbr.ru/scripts/XML_daily.asp";

    public DownloadService() {
        super("DownloadService");
    }

    public static void startDownload(Context appContext) {
        Intent intent = new Intent(appContext, DownloadService.class);
        intent.setAction(ACTION_START_DOWNLOAD);
        appContext.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_START_DOWNLOAD.equals(action)) {
                startDownloadInService();
            }
        }
    }

    private void startDownloadInService() {
        String curXml = NetworkUtils.downloadFileToString(CURRENCY_RATE_ADDRESS);
    }
}
