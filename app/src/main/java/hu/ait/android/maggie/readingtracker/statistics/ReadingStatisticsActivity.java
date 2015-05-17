package hu.ait.android.maggie.readingtracker.statistics;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.InjectView;
import hu.ait.android.maggie.readingtracker.R;

public class ReadingStatisticsActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_statistics);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new StatisticsPagerAdapter(getSupportFragmentManager(), getApplicationContext()));

    }
}
