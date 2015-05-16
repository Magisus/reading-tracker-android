package hu.ait.android.maggie.readingtracker.statistics;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
