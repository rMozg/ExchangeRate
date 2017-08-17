package ru.mozgovoy.oleg.exchangerate.ui.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ru.mozgovoy.oleg.exchangerate.R;
import ru.mozgovoy.oleg.exchangerate.model.MyApplication;
import ru.mozgovoy.oleg.exchangerate.model.core.Currency;
import ru.mozgovoy.oleg.exchangerate.ui.presenter.IRatePresenter;
import ru.mozgovoy.oleg.exchangerate.ui.presenter.RatePresenter;

public class RateActivity extends AppCompatActivity implements IRateView {

    private static final int MAX_SCALE = 3;

    private SwipeRefreshLayout swipeRefreshLayout;
    private Spinner spinnerFrom;
    private Spinner spinnerTo;
    private Button buttonConvert;
    private EditText editFrom;
    private TextView textTo;

    private IRatePresenter ratePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swype_layout);
        spinnerFrom = (Spinner) findViewById(R.id.spinner_from);
        spinnerTo = (Spinner) findViewById(R.id.spinner_to);
        buttonConvert = (Button) findViewById(R.id.button_convert);
        editFrom = (EditText) findViewById(R.id.edit_from);
        textTo = (TextView) findViewById(R.id.text_to);

        ratePresenter = new RatePresenter(getResources(), this, MyApplication.getInstance().getStorage());

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                ratePresenter.startDownloadNewRates(getApplicationContext());
            }
        });
        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    BigDecimal sumfrom = new BigDecimal(editFrom.getText().toString());
                    Object curFromObject = spinnerFrom.getSelectedItem();
                    Object curToObject = spinnerTo.getSelectedItem();
                    if ((curFromObject instanceof CurrencyView) && (curToObject instanceof CurrencyView)) {
                        ratePresenter.startRecalculate(
                                sumfrom,
                                ((CurrencyView) curFromObject).getCurrency(),
                                ((CurrencyView) curToObject).getCurrency());
                    } else {
                        ((IRateView) RateActivity.this).showError(ErrorType.CALCULATE);
                    }
                } catch (NumberFormatException exc) {
                    exc.printStackTrace();
                    ((IRateView) RateActivity.this).showError(ErrorType.CALCULATE);
                }
            }
        });
        editFrom.setFilters(new InputFilter[]{new MoneyValueFilter(MAX_SCALE)});

    }

    @Override
    protected void onResume() {
        super.onResume();
        ratePresenter.startDownloadNewRates(getApplicationContext());
    }

    @Override
    public void setCurrency(List<Currency> currencies) {
        swipeRefreshLayout.setRefreshing(false);

        ArrayAdapter<CurrencyView> adapterFrom = new ArrayAdapter<CurrencyView>(this, android.R.layout.simple_spinner_item, fromCurrencies(currencies));
        adapterFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapterFrom);

        ArrayAdapter<CurrencyView> adapterTo = new ArrayAdapter<CurrencyView>(this, android.R.layout.simple_spinner_item, fromCurrencies(currencies));
        adapterTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTo.setAdapter(adapterFrom);

    }

    @Override
    public void showRecalculatedValue(BigDecimal value) {
        textTo.setText(value.setScale(MAX_SCALE, BigDecimal.ROUND_HALF_UP).toPlainString());
    }

    @Override
    public void showError(ErrorType errorType) {
        switch (errorType) {
            case CALCULATE:

                break;
            case DOWNLOAD:

                swipeRefreshLayout.setRefreshing(false);
                break;
        }
    }

    private static class MoneyValueFilter extends DigitsKeyListener {
        private int digits = 2;

        public MoneyValueFilter(int scale) {
            super(false, true);
            digits = scale;
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            CharSequence out = super.filter(source, start, end, dest, dstart, dend);
            if (out != null) {
                source = out;
                start = 0;
                end = out.length();
            }
            int len = end - start;
            if (len == 0) {
                return source;
            }
            int dlen = dest.length();
            for (int i = 0; i < dstart; i++) {
                if (dest.charAt(i) == '.') {
                    return (dlen - (i + 1) + len > digits) ? "" : new SpannableStringBuilder(source, start, end);
                }
            }
            for (int i = start; i < end; ++i) {
                if (source.charAt(i) == '.') {
                    if ((dlen - dend) + (end - (i + 1)) > digits)
                        return "";
                    else
                        break;
                }
            }
            return new SpannableStringBuilder(source, start, end);
        }
    }

    private List<CurrencyView> fromCurrencies(List<Currency> currencies) {
        if (currencies == null) {
            return null;
        }
        List<CurrencyView> currencyViews = new ArrayList<>(currencies.size());
        for (Currency currency : currencies) {
            currencyViews.add(new CurrencyView(currency));
        }
        Collections.sort(currencyViews, new Comparator<CurrencyView>() {
            @Override
            public int compare(CurrencyView currencyView, CurrencyView another) {
                return currencyView.toString().compareTo(another.toString());
            }
        });
        return currencyViews;
    }

    private static class CurrencyView {
        private Currency currency;

        public CurrencyView(Currency currency) {
            this.currency = currency;
        }

        public Currency getCurrency() {
            return currency;
        }

        @Override
        public String toString() {
            return currency.getName();
        }
    }
}
