/*
 * Copyright (c) 2016 Erik Cox
 */

package rocks.ecox.stockhawk.ui;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import rocks.ecox.stockhawk.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Replace placeholder with DetailFragment instance
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_container, new DetailFragment())
                    .commit();
        }
    }
}