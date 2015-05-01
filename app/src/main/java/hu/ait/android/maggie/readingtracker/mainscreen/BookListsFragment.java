package hu.ait.android.maggie.readingtracker.mainscreen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hu.ait.android.maggie.readingtracker.R;
import hu.ait.android.maggie.readingtracker.books.Book;
import hu.ait.android.maggie.readingtracker.books.CategoriesExpandableAdapter;

/**
 * Created by Magisus on 5/1/2015.
 */
public class BookListsFragment extends Fragment {

    public static final String TAG = "BookListFragment";

    private String[] categories;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        categories = getResources().getStringArray(R.array.reading_status_array);

        View rootView = inflater.inflate(R.layout.book_lists_fragment, container, false);

        ExpandableListView bookListsExp = (ExpandableListView) rootView.findViewById(R.id
                .bookListsExp);
        bookListsExp.setAdapter(new CategoriesExpandableAdapter(getActivity(), categories,
                getBookLists()));

        return rootView;
    }

    private HashMap<String, List<Book>> getBookLists() {
        HashMap<String, List<Book>> lists = new HashMap<>();
        //lists.put(categories[0], Book.find(Book.class, "status = ?", "IN_PROGRESS"));
        List<Book> test = new ArrayList<>();
        test.add(new Book("Jane Eyre", "Charlotte Bronte", null));
        test.add(new Book("The Count of Monte Cristo", "Alexander Dumas", null));
        lists.put(categories[0], test);
        lists.put(categories[1], Book.find(Book.class, "status = ?", "TO_READ"));
        lists.put(categories[2], Book.find(Book.class, "status = ?", "FINISHED"));
        return lists;
    }
}
