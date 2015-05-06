package hu.ait.android.maggie.readingtracker.details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.ButterKnife;
import butterknife.InjectView;
import hu.ait.android.maggie.readingtracker.R;
import hu.ait.android.maggie.readingtracker.books.Book;

/**
 * Created by Magisus on 5/6/2015.
 */
public class BookDetailsFragment extends Fragment {

    public static final String BOOK_TO_DISPLAY = "BOOK_TO_DISPLAY";
    public static final String TAG = "BookDetailsFragment";

    @InjectView(R.id.titleText)
    TextView titleText;

    @InjectView(R.id.authorText)
    TextView authorText;

    @InjectView(R.id.coverImage)
    ImageView coverImage;

    @InjectView(R.id.pagesText)
    TextView pagesText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.book_details_fragment, container, false);
        ButterKnife.inject(this, rootView);

        Book book = (Book) getArguments().getSerializable(BOOK_TO_DISPLAY);
        fillDetails(book);

        return rootView;
    }

    private void fillDetails(Book book){
        titleText.setText(book.getTitle());
        authorText.setText(book.getAuthor());

        if(book.getCoverUrl() != null) {
            Glide.with(getActivity().getApplicationContext()).load(book.getCoverUrl()).into(coverImage);
        }

        pagesText.setText("Pages: " + book.getPageCount());
    }
}
