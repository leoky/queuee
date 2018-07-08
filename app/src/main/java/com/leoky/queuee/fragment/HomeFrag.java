package com.leoky.queuee.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leoky.queuee.R;
import com.leoky.queuee.activity.MainActivity;
import com.leoky.queuee.session.SessionManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFrag extends Fragment {
    private SwitchCompat sw;
    private Toolbar toolbar;

    public HomeFrag() {
        // Required empty public constructor
    }

    public static HomeFrag newInstance() {
        
        Bundle args = new Bundle();
        
        HomeFrag fragment = new HomeFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        toolbar = v.findViewById(R.id.toolbar);
        sw = toolbar.findViewById(R.id.sw);

        updateView();
        return v;
    }
    private void updateView(){
        sw.setChecked((MainActivity.sp.getSpCStatus().equals("Open")? true : false));
    }


}
