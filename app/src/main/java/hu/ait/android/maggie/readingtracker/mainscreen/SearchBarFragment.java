package hu.ait.android.maggie.readingtracker.mainscreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import hu.ait.android.maggie.readingtracker.GetBooksTask;
import hu.ait.android.maggie.readingtracker.R;
import hu.ait.android.maggie.readingtracker.books.Book;
import hu.ait.android.maggie.readingtracker.books_json.BookSearchResult;
import hu.ait.android.maggie.readingtracker.books_json.Item;

/**
 * Created by Magisus on 5/2/2015.
 */
public class SearchBarFragment extends Fragment {

    public interface BookResultsDisplay {
        public void showSearchResults(ArrayList<Book> books);
    }

    public static final String TAG = "SearchBarFragment";

    @InjectView(R.id.searchEdit)
    EditText searchEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_bar, container, false);
        ButterKnife.inject(this, rootView);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.searchBtn)
    public void goToAddBookScreen(View view) {
        String searchTerm = searchEdit.getText().toString();
        searchTerm = searchTerm.replace(" ", "%20");

        String query = "https://www.googleapis.com/books/v1/volumes?q=" + searchTerm;

        new GetBooksTask(getActivity().getApplicationContext()).execute(query);
    }


    public void onEventMainThread(BookSearchResult result) {
        ArrayList<Book> booksSearched = new ArrayList<>();
        for (Item item : result.getItems()) {
            String title = item.getVolumeInfo().getTitle();
            String authorList = "";
            List<String> authors = item.getVolumeInfo().getAuthors();
            for (int i = 0; i < authors.size(); i++) {
                authorList += authors.get(i);
                if (i != authors.size() - 1) {
                    authorList += ", ";
                }
            }

            String coverUrl = null;
            if (item.getVolumeInfo().getImageLinks() != null) {
                coverUrl = item.getVolumeInfo().getImageLinks().getSmallThumbnail();
            }

            booksSearched.add(new Book(title, authorList, coverUrl));
        }
        ((BookResultsDisplay) getActivity()).showSearchResults(booksSearched);
    }
}
