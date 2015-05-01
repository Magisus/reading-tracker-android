package hu.ait.android.maggie.readingtracker.mainscreen;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import hu.ait.android.maggie.readingtracker.R;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showFragment(BookListsFragment.TAG);
    }

    private void showFragment(String fragmentTag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();

        if (BookListsFragment.TAG.equals(fragmentTag)) {
            BookListsFragment mainMenuFragment = new BookListsFragment();
            fragmentTransaction.replace(R.id.listsContainer, mainMenuFragment,
                    BookListsFragment.TAG);

            fragmentTransaction.commit();
        }
    }

}
