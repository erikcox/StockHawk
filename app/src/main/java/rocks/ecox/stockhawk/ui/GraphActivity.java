/*
 * Copyright (c) 2016 Erik Cox
 */

package rocks.ecox.stockhawk.ui;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.XAxisValueFormatter;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.io.IOException;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import au.com.bytecode.opencsv.CSVReader;
import butterknife.BindView;
import butterknife.ButterKnife;
import rocks.ecox.stockhawk.R;
import rocks.ecox.stockhawk.data.Contract;
import timber.log.Timber;

public class GraphActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int LOADER_ID = 0;
    @BindView(R.id.symbol)
    TextView symbol;
    @BindView(R.id.chart)
    LineChart chart;
    private YAxisValueFormatter yFormatter;
    private XAxisValueFormatter xFormatter;

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
                getIntent().getData(),
                Contract.Quote.QUOTE_COLUMNS,
                null, null, Contract.Quote.COLUMN_SYMBOL);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        data.moveToFirst();

        try {
            // Read stock data
            CSVReader reader = new CSVReader(new StringReader(data.getString(Contract.Quote.POSITION_HISTORY)));

            List<String[]> lines = reader.readAll();
            ArrayList<String> dates = new ArrayList<>();
            ArrayList<Entry> entries = new ArrayList<>();

            int j = 0;
            for (int i = lines.size() - 1; i >= 0; i--) {
                String[] nextLine = lines.get(i);
                dates.add(nextLine[0]);
                entries.add(new Entry(Float.parseFloat(nextLine[1]), j));
                j++;
            }

            LineDataSet dataSet = new LineDataSet(entries, data.getString(Contract.Quote.POSITION_SYMBOL));

            int textColor = Color.WHITE;

            dataSet.setValueTextColor(textColor);
            LineData lineData = new LineData(dates, dataSet);

            // Build chart
            chart.setDescription("");
            chart.setNoDataText(getString(R.string.chart_no_data));
            chart.getLegend().setTextColor(textColor);
            chart.setData(lineData);
            chart.getAxisLeft().setValueFormatter(yFormatter);
            chart.getAxisRight().setValueFormatter(yFormatter);
            chart.getXAxis().setValueFormatter(xFormatter);
            chart.getXAxis().setTextColor(textColor);
            chart.getAxisLeft().setTextColor(textColor);
            chart.getAxisRight().setTextColor(textColor);
            chart.invalidate();

        } catch (IOException e) {
            Timber.e(e, "Couldn't read history data");
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        ButterKnife.bind(this);
        getSupportLoaderManager().initLoader(LOADER_ID, null, this);

        yFormatter = new yAxisValueFormatter();
        xFormatter = new xAxisValueFormatter();
    }

    // Format y-axis
    private class yAxisValueFormatter implements YAxisValueFormatter {
        NumberFormat dollarFormat;

        yAxisValueFormatter() {
            dollarFormat = NumberFormat.getCurrencyInstance(Locale.US);
        }

        @Override
        public String getFormattedValue(float value, YAxis yAxis) {
            return dollarFormat.format(value);
        }
    }

    private class xAxisValueFormatter implements XAxisValueFormatter {
        DateFormat dateFormat;

        xAxisValueFormatter() {
            dateFormat = new SimpleDateFormat("MM/yy", Locale.getDefault());
        }

        @Override
        public String getXValue(String original, int index, ViewPortHandler viewPortHandler) {
            return dateFormat.format(Long.parseLong(original));
        }
    }
}
