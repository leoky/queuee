package com.leoky.queuee.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leoky.queuee.R;
import com.leoky.queuee.activity.ChangeEmail;
import com.leoky.queuee.activity.LoginActivity;
import com.leoky.queuee.session.SessionManager;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFrag extends Fragment{

    private LinearLayout email;
    private TextView tvName, tvEmail,tvDate,tvGender,tvCName,tvLocation,tvTime,tvPhone;
    private ImageView img;
    private Button btnLogout;

    private SessionManager sp;

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

        email = v.findViewById(R.id.llEmail);
        tvName =v.findViewById(R.id.tvName);
        tvEmail =v.findViewById(R.id.tvEmail);
        tvDate =v.findViewById(R.id.tvDate);
        tvGender =v.findViewById(R.id.tvGender);
        tvCName =v.findViewById(R.id.tvCName);
        tvLocation =v.findViewById(R.id.tvLocation);
        tvTime =v.findViewById(R.id.tvTime);
        tvPhone =v.findViewById(R.id.tvPhone);
        img = v.findViewById(R.id.imgNow);
        btnLogout = v.findViewById(R.id.btn_logout);

        sp =  new SessionManager(getContext());
        UpdateData();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Log Out")
                        .setMessage("Are you sure?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                sp.clearSp();
                                getActivity().finish();
                                startActivity(new Intent(getActivity(),LoginActivity.class));

                                dialog.dismiss();
                            }
                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                }).show();

            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ChangeEmail.class);
//                i.putExtra(ChangeEmail.KEY_DATA,tvEmail.getText().toString());
//                i.putExtra(ChangeEmail.KEY_ID,sp.getSpId());
                startActivity(i);
            }
        });
        return v;
    }
    @Override
    public void onResume() {
        super.onResume();
        UpdateData();
    }


    private void UpdateData() {
        if (sp != null) {
            Picasso.get().load(sp.getSpPhoto()).into(img);
            tvEmail.setText(sp.getSpEmail());
            tvName.setText(sp.getSpName());
            tvDate.setText(sp.getSpDOB());
            tvGender.setText(sp.getSpGender());
            tvCName.setText(sp.getSpCName());
            tvLocation.setText(sp.getSpLocation());
            tvTime.setText(sp.getSpTime());
            tvPhone.setText(sp.getSpPhone());
        }
    }

}
