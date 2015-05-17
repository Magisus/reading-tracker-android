package hu.ait.android.maggie.readingtracker.statistics;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import hu.ait.android.maggie.readingtracker.R;
import hu.ait.android.maggie.readingtracker.books.Book;

/**
 * Created by Magisus on 5/16/2015.
 */
public class BookStatsFragment extends Fragment {

    @InjectView(R.id.booksFinishedText)
    TextView finishedCountText;

    @InjectView(R.id.averageLengthText)
    TextView averageLengthText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.book_stats_fragment, container, false);
        ButterKnife.inject(this, rootView);

        populateData();

        return rootView;
    }

    private void populateData() {
        Resources res = getResources();
        List<Book> finishedBooks = Book.find(Book.class, "status = ?", "FINISHED");
        finishedCountText.setText(res.getString(R.string.books_finished_label) + finishedBooks.size());

        averageLengthText.setText(res.getString(R.string.average_length_label) +
                getAverageBookLength(finishedBooks) + " " +
                res.getString(R.string.pages));
    }

    private int getAverageBookLength(List<Book> finishedBooks) {
        double totalPages = 0.0;
        for(Book book : finishedBooks){
            totalPages += book.getPageCount();
        }
        return (int) (totalPages / finishedBooks.size());
    }
}
