package hu.ait.android.maggie.readingtracker.mainscreen;

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
import hu.ait.android.maggie.readingtracker.R;
import hu.ait.android.maggie.readingtracker.books.Book;
import hu.ait.android.maggie.readingtracker.books.BookAdapter;

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

            }
        });

        return rootView;
    }
}
