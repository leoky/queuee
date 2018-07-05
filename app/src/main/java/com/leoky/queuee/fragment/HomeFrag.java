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
public class HomeFrag extends Fragment {


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
        return v;
    }


}
