package hu.ait.android.maggie.readingtracker.mainscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import hu.ait.android.maggie.readingtracker.R;
import hu.ait.android.maggie.readingtracker.books.Book;
import hu.ait.android.maggie.readingtracker.books_json.BookSearchResult;
import hu.ait.android.maggie.readingtracker.books_json.Item;
import hu.ait.android.maggie.readingtracker.create.CreateBookActivity;


public class MainActivity extends ActionBarActivity implements SearchBarFragment.BookResultsDisplay {

    public static final int CREATE_BOOK = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        showListsFragment();
        showSearchFragment();
    }

    private void showListsFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();

        BookListsFragment mainMenuFragment = new BookListsFragment();
        fragmentTransaction.replace(R.id.listsContainer, mainMenuFragment,
                BookListsFragment.TAG);

        fragmentTransaction.commit();
    }

    private void showSearchFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();

        SearchBarFragment mainMenuFragment = new SearchBarFragment();
        fragmentTransaction.replace(R.id.searchContainer, mainMenuFragment,
                SearchBarFragment.TAG);

        fragmentTransaction.commit();
    }

    public void showSearchResults(ArrayList<Book> results) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();

        SearchResultsFragment fragment = new SearchResultsFragment();
        Bundle args = new Bundle();
        args.putSerializable(SearchResultsFragment.SEARCH_RESULTS, results);
        fragment.setArguments(args);
        fragmentTransaction.replace(R.id.listsContainer, fragment, SearchResultsFragment.TAG);
        fragmentTransaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (CREATE_BOOK == requestCode && resultCode == RESULT_OK) {
            Book book = (Book) data.getSerializableExtra(CreateBookActivity.KEY_NEW_BOOK);
            book.save();

            BookListsFragment fragment = (BookListsFragment) getSupportFragmentManager()
                    .findFragmentByTag(BookListsFragment.TAG);
            if (fragment != null) {
                fragment.addBook(book);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
