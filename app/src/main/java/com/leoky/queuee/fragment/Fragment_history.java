package com.leoky.queuee.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leoky.queuee.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_history extends Fragment {


    public Fragment_history() {
        // Required empty public constructor
    }

    public static Fragment_history newInstance() {
        
        Bundle args = new Bundle();
        
        Fragment_history fragment = new Fragment_history();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_history, container, false);
    }

}
