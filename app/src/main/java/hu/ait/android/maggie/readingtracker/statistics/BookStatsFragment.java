package hu.ait.android.maggie.readingtracker.statistics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import hu.ait.android.maggie.readingtracker.R;

/**
 * Created by Magisus on 5/16/2015.
 */
public class BookStatsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.book_stats_fragment, container, false);
        ButterKnife.inject(this, rootView);

        return rootView;
    }
}
