package hu.ait.android.maggie.readingtracker.mainscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.ait.android.maggie.readingtracker.GetBooksTask;
import hu.ait.android.maggie.readingtracker.R;
import hu.ait.android.maggie.readingtracker.books.Book;
import hu.ait.android.maggie.readingtracker.create.CreateBookActivity;


public class MainActivity extends ActionBarActivity {

    public static final int CREATE_BOOK = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (CREATE_BOOK == requestCode && resultCode == RESULT_OK) {
            Book book = (Book) data.getSerializableExtra(CreateBookActivity.KEY_NEW_BOOK);
            book.save();

            BookListsFragment fragment = (BookListsFragment) getSupportFragmentManager()
                    .findFragmentByTag(BookListsFragment.TAG);
            if(fragment != null){
                fragment.addBook(book);
            }
        }
    }

    @OnClick(R.id.addBookBtn)
    public void goToAddBookScreen(View view) {
        String query = "https://www.googleapis.com/books/v1/volumes?q=flowers+inauthor:keyes";

        new GetBooksTask(getApplicationContext()).execute(query);
        //startActivityForResult(new Intent(this, CreateBookActivity.class), CREATE_BOOK);
    }

}
