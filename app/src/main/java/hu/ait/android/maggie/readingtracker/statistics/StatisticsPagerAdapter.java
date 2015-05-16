package hu.ait.android.maggie.readingtracker.statistics;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import hu.ait.android.maggie.readingtracker.R;
import hu.ait.android.maggie.readingtracker.details.ReadingDatesDialog;

/**
 * Created by Magisus on 5/16/2015.
 */
public class StatisticsPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public StatisticsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ReadingHistoryFragment();
            case 1:
                return new ReadingRateFragment();
            case 2:
                return new BookStatsFragment();
            default:
                return new ReadingHistoryFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return context.getString(R.string.reading_history_tab);
            case 1:
                return context.getString(R.string.reading_rate_tab);
            case 2:
                return context.getString(R.string.book_stats_tab);
            default:
                return context.getString(R.string.reading_history_tab);
        }
    }
}
