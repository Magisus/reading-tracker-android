package hu.ait.android.maggie.readingtracker.mainscreen;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import hu.ait.android.maggie.readingtracker.R;
import hu.ait.android.maggie.readingtracker.books.Book;
import hu.ait.android.maggie.readingtracker.books.CategoriesExpandableAdapter;
import hu.ait.android.maggie.readingtracker.details.BookDetailsActivity;
import hu.ait.android.maggie.readingtracker.details.BookDetailsFragment;

/**
 * Created by Magisus on 5/1/2015.
 */
public class BookListsFragment extends Fragment {

    public static final String TAG = "BookListFragment";

    private String[] categories;

    @InjectView(R.id.bookListsExp)
    ExpandableListView bookListsExp;

    private CategoriesExpandableAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        categories = getResources().getStringArray(R.array.reading_status_array);

        View rootView = inflater.inflate(R.layout.book_lists_fragment, container, false);
        ButterKnife.inject(this, rootView);

        setUpBookLists();

        return rootView;
    }

    private void setUpBookLists() {
        adapter = new CategoriesExpandableAdapter(getActivity(), categories,
                getBookLists());
        bookListsExp.setAdapter(adapter);
        bookListsExp.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int
                    childPosition, long id) {
                Book book = (Book) bookListsExp.getExpandableListAdapter().getChild
                        (groupPosition, childPosition);
                Intent intent = new Intent(getActivity(), BookDetailsActivity.class);
                intent.putExtra(BookDetailsActivity.BOOK_TO_DISPLAY, book);
                intent.putExtra(BookDetailsActivity.BOOK_ID, book.getId());
                startActivity(intent);
                return true;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        //Need to prompt a poll to the database
        setUpBookLists();
    }

    private HashMap<String, List<Book>> getBookLists() {
        HashMap<String, List<Book>> lists = new HashMap<>();
        lists.put(categories[0], Book.find(Book.class, "status = ?", "IN_PROGRESS"));
        lists.put(categories[1], Book.find(Book.class, "status = ?", "TO_READ"));
        lists.put(categories[2], Book.find(Book.class, "status = ?", "FINISHED"));
        return lists;
    }

    public void addBook(Book book) {
        adapter.addBook(book);
        adapter.notifyDataSetChanged();
    }
}
