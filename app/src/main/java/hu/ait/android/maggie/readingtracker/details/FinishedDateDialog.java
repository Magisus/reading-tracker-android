package hu.ait.android.maggie.readingtracker.details;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.FrameLayout;

import java.util.Date;
import java.util.zip.Inflater;

import hu.ait.android.maggie.readingtracker.R;

/**
 * Created by Magisus on 5/14/2015.
 */
public class FinishedDateDialog extends DialogFragment {

    public interface DateSelectedInterface {
        public void onDateSelected(Date date);
    }

    public static final String TAG = "FinishedDateDialog";

    private DateSelectedInterface dateSelectedInterface;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            dateSelectedInterface = (DateSelectedInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OptionsFragmentInterface");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getActivity().getString(R.string.date_finished_diag_title));
        builder.setCancelable(false);
        builder.setView(getLayoutInflater(savedInstanceState).inflate(R.layout
                .finished_date_dialog, null));
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dateSelectedInterface.onDateSelected();
            }
        });
        return builder.create();
    }
}
