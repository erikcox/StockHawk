/*
 * Copyright (c) 2016 Erik Cox
 */

package rocks.ecox.stockhawk.data;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

@ContentProvider(authority = QuoteProvider.AUTHORITY, database = QuoteDatabase.class)
public class QuoteProvider {
    public static final String AUTHORITY = "rocks.ecox.stockhawk.data.QuoteProvider";
    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    private static Uri buildUri(String... paths) {
        Uri.Builder builder = BASE_CONTENT_URI.buildUpon();
        for (String path : paths) {
            builder.appendPath(path);
        }

        return builder.build();
    }

    interface Path {
        String QUOTES = "quotes";
        String HISTORICAL_QUOTES = "historical_quotes";
    }

    @TableEndpoint(table = QuoteDatabase.QUOTES)
    public static class Quotes {
        @ContentUri(
                path = Path.QUOTES,
                type = "vnd.android.cursor.dir/quote"
        )
        public static final Uri CONTENT_URI = buildUri(Path.QUOTES);

        @InexactContentUri(
                name = "QUOTE_ID",
                path = Path.QUOTES + "/*",
                type = "vnd.android.cursor.item/quote",
                whereColumn = QuoteColumns.SYMBOL,
                pathSegment = 1
        )
        public static Uri withSymbol(String symbol) {
            return buildUri(Path.QUOTES, symbol);
        }
    }

    @TableEndpoint(table = QuoteDatabase.HISTORICAL_QUOTES)
    public static class HistoricalQuotes {
        @ContentUri(
                path = Path.HISTORICAL_QUOTES,
                type = "vnd.android.cursor.dir/historical_quote"
        )
        public static final Uri CONTENT_URI = buildUri(Path.HISTORICAL_QUOTES);

        @InexactContentUri(
                name = "HISTORICAL_QUOTE_ID",
                path = Path.HISTORICAL_QUOTES + "/*",
                type = "vnd.android.cursor.item/historical_quote",
                whereColumn = HistoricalQuoteColumns.SYMBOL,
                pathSegment = 1
        )
        public static Uri withSymbol(String symbol) {
            return buildUri(Path.HISTORICAL_QUOTES, symbol);
        }
    }
}