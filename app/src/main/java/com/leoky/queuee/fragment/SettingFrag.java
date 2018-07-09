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
import com.leoky.queuee.activity.ChangePassword;
import com.leoky.queuee.activity.ChangePhone;
import com.leoky.queuee.activity.LoginActivity;
import com.leoky.queuee.activity.MainActivity;
import com.leoky.queuee.session.SessionManager;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFrag extends Fragment{

    private TextView tvName, tvEmail,tvDate,tvGender,tvCName,tvLocation,tvTime,tvPhone;
    private ImageView img;
    private Button btnLogout;


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

        UpdateData();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Log Out")
                        .setMessage("Are you sure?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                MainActivity.sp.clearSp();
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

        v.findViewById(R.id.llEmail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getActivity(), ChangeEmail.class));
            }
        });
        v.findViewById(R.id.llPassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ChangePassword.class));
            }
        });
        v.findViewById(R.id.llPhone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ChangePhone.class));
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
        if (MainActivity.sp != null) {
            Picasso.get().load(MainActivity.sp.getSpPhoto()).into(img);
            tvEmail.setText(MainActivity.sp.getSpEmail());
            tvName.setText(MainActivity.sp.getSpName());
            tvDate.setText(MainActivity.sp.getSpDOB());
            tvGender.setText(MainActivity.sp.getSpGender());
            tvCName.setText(MainActivity.sp.getSpCName());
            tvLocation.setText(MainActivity.sp.getSpLocation());
            tvTime.setText(MainActivity.sp.getSpTime());
            tvPhone.setText(MainActivity.sp.getSpPhone());
        }
    }

}
