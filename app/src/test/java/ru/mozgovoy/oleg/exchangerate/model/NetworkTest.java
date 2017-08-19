package ru.mozgovoy.oleg.exchangerate.model;

import org.junit.Test;

import ru.mozgovoy.oleg.exchangerate.model.network.INetworkHelper;
import ru.mozgovoy.oleg.exchangerate.model.network.NetworkHelper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class NetworkTest {

    @Test
    public void neworkAvailableTest() throws Exception {
        INetworkHelper networkHelper = new NetworkHelper();
        String googleFile = networkHelper.downloadFileToString("https://www.google.com", true);
        assertNotNull(googleFile);
    }

    @Test
    public void neworkUtfTest() throws Exception {
        INetworkHelper networkHelper = new NetworkHelper();
        String sberFile = networkHelper.downloadFileToString("https://www.sberbank.ru", true);
        assertNotNull(sberFile);
        assertEquals(true, sberFile.contains("Сбербанк"));

    }

    @Test
    public void neworkCP1251Test() throws Exception {
        INetworkHelper networkHelper = new NetworkHelper();
        String currencyFile = networkHelper.downloadFileToString("http://www.cbr.ru/scripts/XML_daily.asp", false);
        assertNotNull(currencyFile);
        assertEquals(true, currencyFile.contains("Фунт стерлингов Соединенного королевства"));
    }

}