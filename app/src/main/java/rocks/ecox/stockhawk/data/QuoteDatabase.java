package rocks.ecox.stockhawk.data;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

@Database(version = QuoteDatabase.VERSION)
public class QuoteDatabase {
    public static final int VERSION = 13;

    @Table(QuoteColumns.class)
    public static final String QUOTES = "quotes";

    @Table(QuoteColumns.class)
    public static final String HISTORICAL_QUOTES = "historical_quotes";

    private QuoteDatabase() {
    }
}