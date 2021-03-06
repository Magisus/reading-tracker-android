package hu.ait.android.maggie.readingtracker.details;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

import hu.ait.android.maggie.readingtracker.R;

/**
 * Created by Magisus on 5/14/2015.
 */
public class ReadingDatesDialog extends DialogFragment implements DatePickerDialog
        .OnDateSetListener {


    public static final String START_OR_FINISHED = "START_OR_FINISHED";

    public interface DateSelectedInterface {
        public void onDateSelected(int year, int month, int day);
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
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePicker = new DatePickerDialog(getActivity(), this, calendar.get
                (Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePicker.setTitle(getResources().getString(R.string.choose_date) + getArguments()
                .getString(START_OR_FINISHED) + ": ");
        return datePicker;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        dateSelectedInterface.onDateSelected(year, monthOfYear, dayOfMonth);
    }
}
