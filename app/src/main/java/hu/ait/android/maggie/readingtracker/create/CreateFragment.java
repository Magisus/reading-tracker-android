package hu.ait.android.maggie.readingtracker.create;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import hu.ait.android.maggie.readingtracker.R;
import hu.ait.android.maggie.readingtracker.books.Book;

/**
 * Created by Magisus on 5/1/2015.
 */
public class CreateFragment extends Fragment {

    public static final String TAG = "CreateFragment";

    @InjectView(R.id.titleEdit)
    EditText titleEdit;
    @InjectView(R.id.authorEdit)
    EditText authorEdit;
    @InjectView(R.id.statusSpinner)
    Spinner statusSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View rootView = inflater.inflate(R.layout.create_fragment, container, false);
        ButterKnife.inject(this, rootView);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.reading_status_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(adapter);

        return rootView;
    }

    @OnClick(R.id.saveBtn)
    public void saveBook(View view) {
        Book book = new Book(titleEdit.getText().toString(), authorEdit.getText().toString(), null);
        book.setStatus(Book.Status.fromInt(statusSpinner.getSelectedItemPosition()));

        getActivity().setResult(CreateBookActivity.RESULT_OK, new Intent().putExtra
                (CreateBookActivity.KEY_NEW_BOOK, book));

        getActivity().finish();
    }

    @OnClick(R.id.cancelBtn)
    public void cancel(View view){
        getActivity().setResult(CreateBookActivity.RESULT_CANCELED);
        getActivity().finish();
    }

}
