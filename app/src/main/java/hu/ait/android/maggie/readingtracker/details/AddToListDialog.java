package hu.ait.android.maggie.readingtracker.details;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import hu.ait.android.maggie.readingtracker.R;
import hu.ait.android.maggie.readingtracker.books.Book;

/**
 * Created by Magisus on 5/11/2015.
 */
public class AddToListDialog extends DialogFragment implements DialogInterface.OnClickListener{

    public static final String TAG = "AddToListDialog";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.add_to_list_btn));
        String[] lists = getResources().getStringArray(R.array.reading_status_array);
        builder.setItems(lists, this);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        ((BookDetailsActivity) getActivity()).saveBook(Book.Status.fromInt(which));
    }
}
