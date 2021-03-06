/*
 * Copyright (c) 2016 Erik Cox
 */

package rocks.ecox.stockhawk.sync;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Utils {

    // Create a query for stock history depending on symbol and date range
    public static String buildStockHistoryQuery(String symbol, String startDate, String endDate){
        return "select * from yahoo.finance.historicaldata where symbol = \'" +
                symbol + "\' and startDate = \'" + startDate + "\' and endDate = \'" + endDate + "\'";
    }

    // Grab the date range
    public static String getDate(int daysAgo){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -daysAgo);
        return dateFormat.format(calendar.getTime());
    }
}