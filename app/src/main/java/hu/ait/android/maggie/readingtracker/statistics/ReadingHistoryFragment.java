package hu.ait.android.maggie.readingtracker.statistics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.ButterKnife;
import hu.ait.android.maggie.readingtracker.R;
import hu.ait.android.maggie.readingtracker.books.Book;
import hu.ait.android.maggie.readingtracker.books.BookAdapter;

/**
 * Created by Magisus on 5/16/2015.
 */
public class ReadingHistoryFragment extends ListFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Book> sortedBooks = Book.find(Book.class, "status = ?", "FINISHED");
        Collections.sort(sortedBooks, new Comparator<Book>() {
            @Override
            public int compare(Book lhs, Book rhs) {
                return lhs.getDateFinished() > rhs.getDateFinished() ? -1 : lhs.getDateFinished()
                        < rhs.getDateFinished() ? 1 : 0;
            }
        });
        setListAdapter(new BookAdapter(getActivity(), sortedBooks));
    }
}
