package hu.ait.android.maggie.readingtracker.details;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

import java.util.Calendar;

import hu.ait.android.maggie.readingtracker.R;
import hu.ait.android.maggie.readingtracker.books.Book;

public class BookDetailsActivity extends ActionBarActivity implements SetStatusDialog
        .StatusSelectedInterface, ReadingDatesDialog.DateSelectedInterface {

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
            args.putString(StatusUpdateFragment.DATE_FINISHED, bookToDisplay
                    .getDateFinishedString());
            args.putString(StatusUpdateFragment.DATE_STARTED, bookToDisplay.getDateStartedString());
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

    public void saveBook() {
        if (bookId != -1) {
            bookToDisplay.setId(bookId);
        }
        bookToDisplay.save();
    }

    @Override
    public void onStatusSelected(Book.Status status) {
        bookToDisplay.setStatus(status);
        if (status.equals(Book.Status.FINISHED) || status.equals(Book.Status.IN_PROGRESS)) {
            ReadingDatesDialog finishedDateDialog = new ReadingDatesDialog();
            Bundle args = new Bundle();
            //For title of dialog, are we choosing the start date or the finished date?
            args.putString(ReadingDatesDialog.START_OR_FINISHED, status.equals(Book.Status
                    .FINISHED) ? "finished" : "started");
            finishedDateDialog.setArguments(args);
            finishedDateDialog.show(getSupportFragmentManager(), ReadingDatesDialog
                    .TAG);
        } else {
            saveBook();
            updateUI();
        }
    }

    private void updateUI() {
        showStatusFragment(StatusUpdateFragment.TAG);
    }

    @Override
    public void onDateSelected(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        if (bookToDisplay.getStatus().equals(Book.Status.IN_PROGRESS)) {
            if (bookToDisplay.getDateFinished() > 0 && bookToDisplay.getDateFinished() < calendar
                    .getTimeInMillis()) {
                //User is rereading a book, we only want to save the most recent finish date
                bookToDisplay.setDateFinished(-1L);
            }
            bookToDisplay.setDateStarted(calendar.getTimeInMillis());
        } else if (bookToDisplay.getStatus().equals(Book.Status.FINISHED)) {
            bookToDisplay.setDateFinished(calendar.getTimeInMillis());
        }
        saveBook();
        updateUI();
    }
}
