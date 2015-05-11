package hu.ait.android.maggie.readingtracker.details;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

import hu.ait.android.maggie.readingtracker.R;
import hu.ait.android.maggie.readingtracker.books.Book;

public class BookDetailsActivity extends ActionBarActivity {

    private Book bookToDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        bookToDisplay = (Book) getIntent().getSerializableExtra(BookDetailsFragment
                .BOOK_TO_DISPLAY);

        showBookDetails();
        if (bookToDisplay.getStatus() == null) {
            showStatusFragment(ActionButtonFragment.TAG);
        }
    }

    private void showStatusFragment(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();

        if (ActionButtonFragment.TAG.equals(tag)) {
            ActionButtonFragment fragment = new ActionButtonFragment();
            fragmentTransaction.replace(R.id.actionButtonsContainer, fragment,
                    ActionButtonFragment.TAG);
            fragmentTransaction.commit();
        }
    }

    private void showBookDetails() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();

        BookDetailsFragment fragment = new BookDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(BookDetailsFragment.BOOK_TO_DISPLAY, bookToDisplay);
        fragment.setArguments(args);
        fragmentTransaction.replace(R.id.bookInfoContainer, fragment, BookDetailsFragment.TAG);
        fragmentTransaction.commit();
    }

    public void showAddToListDialog() {
        AddToListDialog addDialog = new AddToListDialog();
        addDialog.show(getSupportFragmentManager(), AddToListDialog.TAG);
    }

    public void saveBook(Book.Status status) {
        bookToDisplay.setStatus(status);
        bookToDisplay.save();
    }
}
