package hu.ait.android.maggie.readingtracker.create;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import hu.ait.android.maggie.readingtracker.R;
import hu.ait.android.maggie.readingtracker.mainscreen.BookListsFragment;

public class CreateBookActivity extends ActionBarActivity {

    public static final String KEY_NEW_BOOK = "KEY_NEW_BOOK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_book);

        showFragment(CreateFragment.TAG);
    }

    private void showFragment(String fragmentTag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();

        if (CreateFragment.TAG.equals(fragmentTag)) {
            CreateFragment mainMenuFragment = new CreateFragment();
            fragmentTransaction.replace(R.id.layoutContainer, mainMenuFragment,
                    CreateFragment.TAG);

            fragmentTransaction.commit();
        }
    }

}
