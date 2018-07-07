package com.leoky.queuee.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leoky.queuee.R;
import com.leoky.queuee.activity.Changedata;
import com.leoky.queuee.activity.MainActivity;
import com.leoky.queuee.adapter.RVList;


import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFrag extends Fragment{

    private LinearLayout email;

    public SettingFrag() {
        // Required empty public constructor
    }

    public static SettingFrag newInstance() {
        
        Bundle args = new Bundle();
        
        SettingFrag fragment = new SettingFrag();

        fragment.setArguments(args);
        return fragment;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_setting, container, false);

        email = v.findViewById(R.id.email);

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Changedata.class);
                startActivity(intent);
            }
        });

        return v;



    }

}
