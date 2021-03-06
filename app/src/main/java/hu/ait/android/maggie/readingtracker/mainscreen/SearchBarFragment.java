package hu.ait.android.maggie.readingtracker.mainscreen;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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
import hu.ait.android.maggie.readingtracker.book_search_json.BookSearchResult;
import hu.ait.android.maggie.readingtracker.book_search_json.Item;

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

        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchEdit.getWindowToken(), 0);
    }

    public void onEventMainThread(BookSearchResult result) {
        //Create list of books from volume information
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

            Book book = new Book(title, authorList, coverUrl);
            book.setApiId(item.getId());

            booksSearched.add(book);
        }
        ((BookResultsDisplay) getActivity()).showSearchResults(booksSearched);
    }
}
