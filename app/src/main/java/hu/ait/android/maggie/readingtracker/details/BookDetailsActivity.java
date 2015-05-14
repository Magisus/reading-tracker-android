package hu.ait.android.maggie.readingtracker.details;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

import java.util.Date;

import hu.ait.android.maggie.readingtracker.R;
import hu.ait.android.maggie.readingtracker.books.Book;

public class BookDetailsActivity extends ActionBarActivity implements SetStatusDialog
        .StatusSelectedInterface, FinishedDateDialog.DateSelectedInterface {

    public static final String BOOK_TO_DISPLAY = "BOOK_TO_DISPLAY";
    public static final String BOOK_ID = "BOOK_ID";

    private Book bookToDisplay;
    private long bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        bookToDisplay = (Book) getIntent().getSerializableExtra(BOOK_TO_DISPLAY);
        bookId = getIntent().getLongExtra(BOOK_ID, -1L);

        showBookDetails();

        if (bookToDisplay.getStatus() == null) {
            showStatusFragment(ActionButtonFragment.TAG);
        } else {
            showStatusFragment(StatusUpdateFragment.TAG);
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
        } else if (StatusUpdateFragment.TAG.equals(tag)) {
            StatusUpdateFragment fragment = new StatusUpdateFragment();
            Bundle args = new Bundle();
            args.putInt(StatusUpdateFragment.STATUS, bookToDisplay.getStatus
                    ().getIndex());
            fragment.setArguments(args);
            fragmentTransaction.replace(R.id.actionButtonsContainer, fragment,
                    StatusUpdateFragment.TAG);
            fragmentTransaction.commit();
        }
    }

    private void showBookDetails() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();

        BookDetailsFragment fragment = new BookDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(BOOK_TO_DISPLAY, bookToDisplay);
        fragment.setArguments(args);
        fragmentTransaction.replace(R.id.bookInfoContainer, fragment, BookDetailsFragment.TAG);
        fragmentTransaction.commit();
    }

    public void showAddToListDialog() {
        SetStatusDialog addDialog = new SetStatusDialog();
        addDialog.show(getSupportFragmentManager(), SetStatusDialog.TAG);
    }

    public void saveBook(Book.Status status) {
        bookToDisplay.setStatus(status);
        if(bookId != -1){
            bookToDisplay.setId(bookId);
        }
        bookToDisplay.save();
    }

    @Override
    public void onStatusSelected(Book.Status status) {
        saveBook(status);
        updateUI();
    }

    public void onFinishedDateSelected(Date date){
        bookToDisplay.setDateFinished(date);
    }

    private void updateUI() {
        showStatusFragment(StatusUpdateFragment.TAG);
    }
}
