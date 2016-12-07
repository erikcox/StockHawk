package rocks.ecox.stockhawk.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rocks.ecox.stockhawk.R;
import timber.log.Timber;


public class DetailFragment extends Fragment {

    private String stock;

    public DetailFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getActivity().getIntent();
        if (intent != null) {
            stock = intent.getStringExtra(Intent.EXTRA_TEXT);
        }

        if (savedInstanceState != null){
            Timber.d("Grab stock history for " + stock);
            //fetchStockHistory();
        }

        // getLoaderManager().initLoader(CURSOR_LOADER_ID, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        // fetchStockHistory();
        return rootView;
    }

}
