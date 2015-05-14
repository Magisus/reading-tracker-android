package hu.ait.android.maggie.readingtracker.details;

import android.app.Activity;
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
public class SetStatusDialog extends DialogFragment implements DialogInterface.OnClickListener {

    public static final String TAG = "AddToListDialog";

    public interface StatusSelectedInterface {
        public void onStatusSelected(Book.Status status);
    }

    private StatusSelectedInterface statusSelectedInterface;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.add_to_list_btn));
        String[] lists = getResources().getStringArray(R.array.reading_status_array);
        builder.setItems(lists, this);
        return builder.create();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            statusSelectedInterface = (StatusSelectedInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OptionsFragmentInterface");
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (Book.Status.fromInt(which).equals(Book.Status.FINISHED)) {
            FinishedDateDialog finishedDateDialog = new FinishedDateDialog();
            finishedDateDialog.show(getActivity().getSupportFragmentManager(), FinishedDateDialog
                    .TAG);
        }
        statusSelectedInterface.onStatusSelected(Book.Status.fromInt(which));
    }
}
