package ru.mozgovoy.oleg.exchangerate.model.network;

import android.support.annotation.Nullable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkHelper implements INetworkHelper {

    @Override
    @Nullable
    public String downloadFileToString(String address) {
        String xmlString = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(address);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                StringBuilder xmlResponse = new StringBuilder();
                BufferedReader input = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "CP1251"), 8192);
                String strLine = null;
                while ((strLine = input.readLine()) != null) {
                    xmlResponse.append(strLine);
                }
                xmlString = xmlResponse.toString();
                input.close();
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return xmlString;
    }
}
