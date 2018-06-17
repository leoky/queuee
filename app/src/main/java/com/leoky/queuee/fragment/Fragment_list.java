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
public class Fragment_list extends Fragment {


    public Fragment_list() {
        // Required empty public constructor
    }

    public static Fragment_list newInstance() {
        
        Bundle args = new Bundle();
        
        Fragment_list fragment = new Fragment_list();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_list, container, false);
    }

}
