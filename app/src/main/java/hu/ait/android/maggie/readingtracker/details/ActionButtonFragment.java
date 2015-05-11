package hu.ait.android.maggie.readingtracker.details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.ait.android.maggie.readingtracker.R;

/**
 * Created by Magisus on 5/11/2015.
 */
public class ActionButtonFragment extends Fragment {

    public static final String TAG = "ActionButtonFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.action_buttons_fragment, container, false);
        ButterKnife.inject(this, rootView);

        return rootView;
    }

    @OnClick(R.id.addToListBtn)
    public void showAddToListDialog(View view){
        ((BookDetailsActivity) getActivity()).showAddToListDialog();
    }
}
