package ru.mozgovoy.oleg.exchangerate.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.os.ResultReceiver;

import ru.mozgovoy.oleg.exchangerate.model.NetworkUtils;
import ru.mozgovoy.oleg.exchangerate.ui.presenter.RatePresenter;

public class DownloadService extends IntentService {

    private static final String ACTION_START_DOWNLOAD = "ru.mozgovoy.oleg.exchangerate.service.action.ACTION_START_DOWNLOAD";
    public static String PARAM_RESULT_RECEIVER = "PARAM_RESULT_RECEIVER";

    public static String CURRENCY_RATE_ADDRESS = "http://www.cbr.ru/scripts/XML_daily.asp";

    public DownloadService() {
        super("DownloadService");
    }

    public static void startDownload(Context appContext, ResultReceiver resultReceiver) {
        Intent intent = new Intent(appContext, DownloadService.class);
        intent.setAction(ACTION_START_DOWNLOAD);
        intent.putExtra(PARAM_RESULT_RECEIVER, resultReceiver);
        appContext.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_START_DOWNLOAD.equals(action)) {
                Parcelable resultReceiverParcelable = intent.getParcelableExtra(PARAM_RESULT_RECEIVER);
                if (resultReceiverParcelable instanceof ResultReceiver) {
                    startDownloadInService((ResultReceiver) resultReceiverParcelable);
                }
            }
        }
    }

    private void startDownloadInService(ResultReceiver resultReceiver) {
        String curXml = NetworkUtils.downloadFileToString(CURRENCY_RATE_ADDRESS);
        Bundle result = new Bundle();
        result.putString(RatePresenter.DownloadResultReceiver.RESULT_OK_PARAM_XML_TEXT, curXml);
        resultReceiver.send(RatePresenter.DownloadResultReceiver.RESULT_OK, result);
    }
}
