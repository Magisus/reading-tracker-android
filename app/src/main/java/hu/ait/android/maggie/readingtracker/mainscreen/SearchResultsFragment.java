package hu.ait.android.maggie.readingtracker.mainscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;
import hu.ait.android.maggie.readingtracker.GetVolumeByIdTask;
import hu.ait.android.maggie.readingtracker.R;
import hu.ait.android.maggie.readingtracker.book_json.VolumeResource;
import hu.ait.android.maggie.readingtracker.books.Book;
import hu.ait.android.maggie.readingtracker.books.BookAdapter;
import hu.ait.android.maggie.readingtracker.details.BookDetailsActivity;
import hu.ait.android.maggie.readingtracker.details.BookDetailsFragment;

/**
 * Created by Magisus on 5/2/2015.
 */
public class SearchResultsFragment extends Fragment {

    public static final String SEARCH_RESULTS = "SEARCH_RESULTS";
    public static final String TAG = "SearchResultsFragment";

    @InjectView(R.id.searchResultsList)
    ListView searchResultsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_results_fragment, container, false);
        ButterKnife.inject(this, rootView);

        List<Book> searchResults = (List<Book>) getArguments().get(SEARCH_RESULTS);
        searchResultsList.setAdapter(new BookAdapter(getActivity(), searchResults));
        searchResultsList.setEmptyView(rootView.findViewById(android.R.id.empty));

        searchResultsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = ((BookAdapter) searchResultsList.getAdapter()).getItem(position);
                String query = "https://www.googleapis.com/books/v1/volumes/" + book.getApiId();

                new GetVolumeByIdTask(getActivity().getApplicationContext()).execute(query);
            }
        });

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

    public void onEventMainThread(VolumeResource volume) {
        String title = volume.getVolumeInfo().getTitle();
        String authorList = "";
        List<String> authors = volume.getVolumeInfo().getAuthors();
        for (int i = 0; i < authors.size(); i++) {
            authorList += authors.get(i);
            if (i != authors.size() - 1) {
                authorList += ", ";
            }
        }

        String coverUrl = null;
        if (volume.getVolumeInfo().getImageLinks() != null) {
            coverUrl = volume.getVolumeInfo().getImageLinks().getMedium();
        }

        Book book = new Book(title, authorList, coverUrl);
        book.setPageCount(volume.getVolumeInfo().getPrintedPageCount());

        Intent intent = new Intent(getActivity(), BookDetailsActivity.class);
        intent.putExtra(BookDetailsFragment.BOOK_TO_DISPLAY, book);
        startActivity(intent);
    }
}
