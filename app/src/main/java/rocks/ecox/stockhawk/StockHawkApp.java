/*
 * Copyright (c) 2016 Erik Cox
 */

package rocks.ecox.stockhawk;

import android.app.Application;

import com.github.mikephil.charting.BuildConfig;

import timber.log.Timber;

public class StockHawkApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.uprootAll();
            Timber.plant(new Timber.DebugTree());
        }
    }
}
