package hu.ait.android.maggie.readingtracker.details;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import hu.ait.android.maggie.readingtracker.R;
import hu.ait.android.maggie.readingtracker.books.Book;

/**
 * Created by Magisus on 5/12/2015.
 */
public class StatusUpdateFragment extends Fragment {

    public static final String TAG = "StatusUpdateFragment";

    public static final String STATUS = "STATUS";

    public static final String DATE_FINISHED = "DATE_FINISHED";
    public static final String DATE_STARTED = "DATE_STARTED";

    @InjectView(R.id.currentStatusText)
    TextView currentStatusText;

    @InjectView(R.id.dateFinishedText)
    TextView dateFinishedText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.status_update_fragment, container, false);
        ButterKnife.inject(this, rootView);

        setStatusText();

        return rootView;
    }

    private void setStatusText() {
        Resources res = getResources();
        Book.Status currentStatus = Book.Status.fromInt(getArguments().getInt(STATUS));
        String[] statuses = res.getStringArray(R.array.reading_status_array);
        currentStatusText.setText(res.getString(R.string.current_status_lable) + statuses[currentStatus.getIndex()]);
        if(currentStatus.equals(Book.Status.FINISHED)){
            String dateFinished = getArguments().getString(DATE_FINISHED);
            dateFinishedText.setText(res.getString(R.string.date_finished_label) + dateFinished);
        } else if (currentStatus.equals(Book.Status.IN_PROGRESS)){
            String dateStarted = getArguments().getString(DATE_STARTED);
            dateFinishedText.setText(res.getString(R.string.date_started_label) + dateStarted);
        }
    }

    @OnClick(R.id.changeStatusBtn)
    public void changeStatus(View view){
        SetStatusDialog setStatusDialog = new SetStatusDialog();
        setStatusDialog.show(getFragmentManager(), SetStatusDialog.TAG);
    }
}
