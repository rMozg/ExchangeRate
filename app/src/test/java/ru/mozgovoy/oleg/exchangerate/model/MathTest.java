package ru.mozgovoy.oleg.exchangerate.model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import ru.mozgovoy.oleg.exchangerate.model.core.Currency;
import ru.mozgovoy.oleg.exchangerate.model.core.CurrencyRate;
import ru.mozgovoy.oleg.exchangerate.model.math.IMathEngine;
import ru.mozgovoy.oleg.exchangerate.model.math.MathEngine;

public class MathTest {

    private static final Currency TEST_CURRENCY_1 = new Currency(123, "QWE", "Qwe Asd");
    private static final Currency TEST_CURRENCY_2 = new Currency(456, "ASD", "Asd Zxc");

    @Test
    public void mathTest1() throws Exception {
        IMathEngine mathEngine = new MathEngine();
        Assert.assertTrue(oneMathOpTest(mathEngine,
                new BigDecimal("100"),
                new BigDecimal("100"),
                100,
                new BigDecimal("100"),
                100,
                new BigDecimal("100")));
        Assert.assertTrue(oneMathOpTest(mathEngine,
                new BigDecimal("100"),
                new BigDecimal("100"),
                100,
                new BigDecimal("100"),
                100,
                new BigDecimal("100")));
    }

    @Test
    public void mathTest2() throws Exception {
        IMathEngine mathEngine = new MathEngine();
        Assert.assertTrue(oneMathOpTest(mathEngine,
                new BigDecimal("1"),
                new BigDecimal("1"),
                1,
                new BigDecimal("1"),
                1,
                new BigDecimal("1")));
    }

    @Test
    public void mathErrTest1() throws Exception {
        IMathEngine mathEngine = new MathEngine();
        Assert.assertFalse(oneMathOpTest(mathEngine,
                new BigDecimal("1"),
                new BigDecimal("2"),
                1,
                new BigDecimal("1"),
                1,
                new BigDecimal("1")));
    }

    private boolean oneMathOpTest(IMathEngine mathEngine, BigDecimal valueFrom, BigDecimal valueTo, int nominalFrom, BigDecimal rateFrom, int nominalTo, BigDecimal rateTo) {
        return valueTo.equals(new BigDecimal(mathEngine.convertCurrency(
                valueFrom,
                new CurrencyRate(TEST_CURRENCY_1, nominalFrom, rateFrom),
                new CurrencyRate(TEST_CURRENCY_2, nominalTo, rateTo)
        ).stripTrailingZeros().toPlainString()));
    }

}
