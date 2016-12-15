/*
 * Copyright (c) 2016 Erik Cox
 */

package rocks.ecox.stockhawk.data;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;

public interface HistoricalQuoteColumns {

    @DataType(DataType.Type.INTEGER)
    @PrimaryKey
    @AutoIncrement
    String _ID =
            "_id";

    @DataType(DataType.Type.TEXT)
    @NotNull
    String SYMBOL = "symbol";

    @DataType(DataType.Type.TEXT)
    @NotNull
    String DATE = "date";

    @DataType(DataType.Type.TEXT)
    @NotNull
    String OPEN = "open";

    @DataType(DataType.Type.TEXT)
    @NotNull
    String CLOSE = "close";
}