package rocks.ecox.stockhawk.service;

import rocks.ecox.stockhawk.sync.StockHistory;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public final class StockHistoryService {

    public interface StockHistoryAPI{
        @GET("v1/public/yql?&format=json&diagnostics=true&env=store://datatables.org/alltableswithkeys&callback=")
        Call<StockHistory> getHistory(
                @Query("q") String query);
    }
}