package ru.mozgovoy.oleg.exchangerate.model;

import org.junit.Test;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import ru.mozgovoy.oleg.exchangerate.model.core.CurrencyRate;
import ru.mozgovoy.oleg.exchangerate.model.exchange.ConverterEngine;
import ru.mozgovoy.oleg.exchangerate.model.exchange.IConverterEngine;

import static org.junit.Assert.assertEquals;

public class ExchangeTest {

    private static int TEST_XML_1_SIZE = 34;

    private static String TEST_XML_1 = "<ValCurs Date=\"19.08.2017\" name=\"Foreign Currency Market\">\n" +
            "<Valute ID=\"R01010\">\n" +
            "<NumCode>036</NumCode>\n" +
            "<CharCode>AUD</CharCode>\n" +
            "<Nominal>1</Nominal>\n" +
            "<Name>Австралийский доллар</Name>\n" +
            "<Value>47,0319</Value>\n" +
            "</Valute>\n" +
            "<Valute ID=\"R01020A\">\n" +
            "<NumCode>944</NumCode>\n" +
            "<CharCode>AZN</CharCode>\n" +
            "<Nominal>1</Nominal>\n" +
            "<Name>Азербайджанский манат</Name>\n" +
            "<Value>35,2083</Value>\n" +
            "</Valute>\n" +
            "<Valute ID=\"R01035\">\n" +
            "<NumCode>826</NumCode>\n" +
            "<CharCode>GBP</CharCode>\n" +
            "<Nominal>1</Nominal>\n" +
            "<Name>Фунт стерлингов Соединенного королевства</Name>\n" +
            "<Value>76,4988</Value>\n" +
            "</Valute>\n" +
            "<Valute ID=\"R01060\">\n" +
            "<NumCode>051</NumCode>\n" +
            "<CharCode>AMD</CharCode>\n" +
            "<Nominal>100</Nominal>\n" +
            "<Name>Армянских драмов</Name>\n" +
            "<Value>12,4048</Value>\n" +
            "</Valute>\n" +
            "<Valute ID=\"R01090B\">\n" +
            "<NumCode>933</NumCode>\n" +
            "<CharCode>BYN</CharCode>\n" +
            "<Nominal>1</Nominal>\n" +
            "<Name>Белорусский рубль</Name>\n" +
            "<Value>30,6222</Value>\n" +
            "</Valute>\n" +
            "<Valute ID=\"R01100\">\n" +
            "<NumCode>975</NumCode>\n" +
            "<CharCode>BGN</CharCode>\n" +
            "<Nominal>1</Nominal>\n" +
            "<Name>Болгарский лев</Name>\n" +
            "<Value>35,6139</Value>\n" +
            "</Valute>\n" +
            "<Valute ID=\"R01115\">\n" +
            "<NumCode>986</NumCode>\n" +
            "<CharCode>BRL</CharCode>\n" +
            "<Nominal>1</Nominal>\n" +
            "<Name>Бразильский реал</Name>\n" +
            "<Value>18,6970</Value>\n" +
            "</Valute>\n" +
            "<Valute ID=\"R01135\">\n" +
            "<NumCode>348</NumCode>\n" +
            "<CharCode>HUF</CharCode>\n" +
            "<Nominal>100</Nominal>\n" +
            "<Name>Венгерских форинтов</Name>\n" +
            "<Value>22,9247</Value>\n" +
            "</Valute>\n" +
            "<Valute ID=\"R01200\">\n" +
            "<NumCode>344</NumCode>\n" +
            "<CharCode>HKD</CharCode>\n" +
            "<Nominal>10</Nominal>\n" +
            "<Name>Гонконгских долларов</Name>\n" +
            "<Value>75,8755</Value>\n" +
            "</Valute>\n" +
            "<Valute ID=\"R01215\">\n" +
            "<NumCode>208</NumCode>\n" +
            "<CharCode>DKK</CharCode>\n" +
            "<Nominal>10</Nominal>\n" +
            "<Name>Датских крон</Name>\n" +
            "<Value>93,6932</Value>\n" +
            "</Valute>\n" +
            "<Valute ID=\"R01235\">\n" +
            "<NumCode>840</NumCode>\n" +
            "<CharCode>USD</CharCode>\n" +
            "<Nominal>1</Nominal>\n" +
            "<Name>Доллар США</Name>\n" +
            "<Value>59,3612</Value>\n" +
            "</Valute>\n" +
            "<Valute ID=\"R01239\">\n" +
            "<NumCode>978</NumCode>\n" +
            "<CharCode>EUR</CharCode>\n" +
            "<Nominal>1</Nominal>\n" +
            "<Name>Евро</Name>\n" +
            "<Value>69,7197</Value>\n" +
            "</Valute>\n" +
            "<Valute ID=\"R01270\">\n" +
            "<NumCode>356</NumCode>\n" +
            "<CharCode>INR</CharCode>\n" +
            "<Nominal>100</Nominal>\n" +
            "<Name>Индийских рупий</Name>\n" +
            "<Value>92,5602</Value>\n" +
            "</Valute>\n" +
            "<Valute ID=\"R01335\">\n" +
            "<NumCode>398</NumCode>\n" +
            "<CharCode>KZT</CharCode>\n" +
            "<Nominal>100</Nominal>\n" +
            "<Name>Казахстанских тенге</Name>\n" +
            "<Value>17,8294</Value>\n" +
            "</Valute>\n" +
            "<Valute ID=\"R01350\">\n" +
            "<NumCode>124</NumCode>\n" +
            "<CharCode>CAD</CharCode>\n" +
            "<Nominal>1</Nominal>\n" +
            "<Name>Канадский доллар</Name>\n" +
            "<Value>46,9444</Value>\n" +
            "</Valute>\n" +
            "<Valute ID=\"R01370\">\n" +
            "<NumCode>417</NumCode>\n" +
            "<CharCode>KGS</CharCode>\n" +
            "<Nominal>100</Nominal>\n" +
            "<Name>Киргизских сомов</Name>\n" +
            "<Value>85,9933</Value>\n" +
            "</Valute>\n" +
            "<Valute ID=\"R01375\">\n" +
            "<NumCode>156</NumCode>\n" +
            "<CharCode>CNY</CharCode>\n" +
            "<Nominal>10</Nominal>\n" +
            "<Name>Китайских юаней</Name>\n" +
            "<Value>88,8760</Value>\n" +
            "</Valute>\n" +
            "<Valute ID=\"R01500\">\n" +
            "<NumCode>498</NumCode>\n" +
            "<CharCode>MDL</CharCode>\n" +
            "<Nominal>10</Nominal>\n" +
            "<Name>Молдавских леев</Name>\n" +
            "<Value>33,2091</Value>\n" +
            "</Valute>\n" +
            "<Valute ID=\"R01535\">\n" +
            "<NumCode>578</NumCode>\n" +
            "<CharCode>NOK</CharCode>\n" +
            "<Nominal>10</Nominal>\n" +
            "<Name>Норвежских крон</Name>\n" +
            "<Value>74,7246</Value>\n" +
            "</Valute>\n" +
            "<Valute ID=\"R01565\">\n" +
            "<NumCode>985</NumCode>\n" +
            "<CharCode>PLN</CharCode>\n" +
            "<Nominal>1</Nominal>\n" +
            "<Name>Польский злотый</Name>\n" +
            "<Value>16,2767</Value>\n" +
            "</Valute>\n" +
            "<Valute ID=\"R01585F\">\n" +
            "<NumCode>946</NumCode>\n" +
            "<CharCode>RON</CharCode>\n" +
            "<Nominal>1</Nominal>\n" +
            "<Name>Румынский лей</Name>\n" +
            "<Value>15,1967</Value>\n" +
            "</Valute>\n" +
            "<Valute ID=\"R01589\">\n" +
            "<NumCode>960</NumCode>\n" +
            "<CharCode>XDR</CharCode>\n" +
            "<Nominal>1</Nominal>\n" +
            "<Name>СДР (специальные права заимствования)</Name>\n" +
            "<Value>83,4357</Value>\n" +
            "</Valute>\n" +
            "<Valute ID=\"R01625\">\n" +
            "<NumCode>702</NumCode>\n" +
            "<CharCode>SGD</CharCode>\n" +
            "<Nominal>1</Nominal>\n" +
            "<Name>Сингапурский доллар</Name>\n" +
            "<Value>43,5040</Value>\n" +
            "</Valute>\n" +
            "<Valute ID=\"R01670\">\n" +
            "<NumCode>972</NumCode>\n" +
            "<CharCode>TJS</CharCode>\n" +
            "<Nominal>10</Nominal>\n" +
            "<Name>Таджикских сомони</Name>\n" +
            "<Value>67,3411</Value>\n" +
            "</Valute>\n" +
            "<Valute ID=\"R01700J\">\n" +
            "<NumCode>949</NumCode>\n" +
            "<CharCode>TRY</CharCode>\n" +
            "<Nominal>1</Nominal>\n" +
            "<Name>Турецкая лира</Name>\n" +
            "<Value>16,8157</Value>\n" +
            "</Valute>\n" +
            "<Valute ID=\"R01710A\">\n" +
            "<NumCode>934</NumCode>\n" +
            "<CharCode>TMT</CharCode>\n" +
            "<Nominal>1</Nominal>\n" +
            "<Name>Новый туркменский манат</Name>\n" +
            "<Value>16,9386</Value>\n" +
            "</Valute>\n" +
            "<Valute ID=\"R01717\">\n" +
            "<NumCode>860</NumCode>\n" +
            "<CharCode>UZS</CharCode>\n" +
            "<Nominal>1000</Nominal>\n" +
            "<Name>Узбекских сумов</Name>\n" +
            "<Value>14,2889</Value>\n" +
            "</Valute>\n" +
            "<Valute ID=\"R01720\">\n" +
            "<NumCode>980</NumCode>\n" +
            "<CharCode>UAH</CharCode>\n" +
            "<Nominal>10</Nominal>\n" +
            "<Name>Украинских гривен</Name>\n" +
            "<Value>23,2835</Value>\n" +
            "</Valute>\n" +
            "<Valute ID=\"R01760\">\n" +
            "<NumCode>203</NumCode>\n" +
            "<CharCode>CZK</CharCode>\n" +
            "<Nominal>10</Nominal>\n" +
            "<Name>Чешских крон</Name>\n" +
            "<Value>26,7068</Value>\n" +
            "</Valute>\n" +
            "<Valute ID=\"R01770\">\n" +
            "<NumCode>752</NumCode>\n" +
            "<CharCode>SEK</CharCode>\n" +
            "<Nominal>10</Nominal>\n" +
            "<Name>Шведских крон</Name>\n" +
            "<Value>72,9683</Value>\n" +
            "</Valute>\n" +
            "<Valute ID=\"R01775\">\n" +
            "<NumCode>756</NumCode>\n" +
            "<CharCode>CHF</CharCode>\n" +
            "<Nominal>1</Nominal>\n" +
            "<Name>Швейцарский франк</Name>\n" +
            "<Value>61,7381</Value>\n" +
            "</Valute>\n" +
            "<Valute ID=\"R01810\">\n" +
            "<NumCode>710</NumCode>\n" +
            "<CharCode>ZAR</CharCode>\n" +
            "<Nominal>10</Nominal>\n" +
            "<Name>Южноафриканских рэндов</Name>\n" +
            "<Value>44,8324</Value>\n" +
            "</Valute>\n" +
            "<Valute ID=\"R01815\">\n" +
            "<NumCode>410</NumCode>\n" +
            "<CharCode>KRW</CharCode>\n" +
            "<Nominal>1000</Nominal>\n" +
            "<Name>Вон Республики Корея</Name>\n" +
            "<Value>52,0229</Value>\n" +
            "</Valute>\n" +
            "<Valute ID=\"R01820\">\n" +
            "<NumCode>392</NumCode>\n" +
            "<CharCode>JPY</CharCode>\n" +
            "<Nominal>100</Nominal>\n" +
            "<Name>Японских иен</Name>\n" +
            "<Value>54,4174</Value>\n" +
            "</Valute>\n" +
            "</ValCurs>";

    private static String TEST_XML_2_TEMPLATE = "<ValCurs Date=\"19.08.2017\" name=\"Foreign Currency Market\">\n" +
            "<Valute ID=\"%1$s\">\n" +
            "<NumCode>%2$d</NumCode>\n" +
            "<CharCode>%3$s</CharCode>\n" +
            "<Nominal>%4$d</Nominal>\n" +
            "<Name>%5$s</Name>\n" +
            "<Value>%6$s</Value>\n" +
            "</Valute>\n" +
            "</ValCurs>";

    @Test
    public void sizeForBigXml() throws Exception {
        IConverterEngine converterEngine = new ConverterEngine();
        List<CurrencyRate> currencyRates = converterEngine.fromXml(TEST_XML_1);
        assertEquals(TEST_XML_1_SIZE, currencyRates.size());
    }

    @Test
    public void parseToModel() throws Exception {
        IConverterEngine converterEngine = new ConverterEngine();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            String id = UUID.randomUUID().toString();
            int code = random.nextInt();
            String charcode = UUID.randomUUID().toString();
            int nominal = random.nextInt();
            String name = UUID.randomUUID().toString();
            String value = random.nextInt() + "," + Math.abs(random.nextInt());
            String xml = String.format(TEST_XML_2_TEMPLATE, id, code, charcode, nominal, name, value);
            List<CurrencyRate> currencyRates = converterEngine.fromXml(xml);
            assertEquals(1, currencyRates.size());
            CurrencyRate currencyRate = currencyRates.get(0);
            assertEquals(code, currencyRate.getCurrency().getCode());
            assertEquals(name, currencyRate.getCurrency().getName());
            assertEquals(charcode, currencyRate.getCurrency().getShortName());
            assertEquals(nominal, currencyRate.getNominal());
            assertEquals(value.replace(",", "."), currencyRate.getValue().toPlainString());
        }
        List<CurrencyRate> currencyRates = converterEngine.fromXml(TEST_XML_1);
        assertEquals(TEST_XML_1_SIZE, currencyRates.size());
    }

}