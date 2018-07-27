package com.leoky.queuee.fragment;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.SettingInjectorService;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.leoky.queuee.R;
import com.leoky.queuee.activity.ChangeEmail;
import com.leoky.queuee.activity.ChangePassword;
import com.leoky.queuee.activity.ChangePhone;
import com.leoky.queuee.activity.CustomTimePopup;
import com.leoky.queuee.activity.LoginActivity;
import com.leoky.queuee.activity.MainActivity;
import com.leoky.queuee.api.model.UserData;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFrag extends Fragment{
    Dialog dialoggantiwaktu;
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
        dialoggantiwaktu = new Dialog(this.getActivity());


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


        v.findViewById(R.id.llTime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tutup;
                dialoggantiwaktu.setContentView(R.layout.activity_custom_time_popup);
                dialoggantiwaktu.show();
                final EditText waktu,error,success;
                final Button UpdateWaktu;
                waktu=dialoggantiwaktu.findViewById(R.id.waktu);
                tutup=dialoggantiwaktu.findViewById(R.id.tutup);
                error=dialoggantiwaktu.findViewById(R.id.error);
                success=dialoggantiwaktu.findViewById(R.id.success);
                UpdateWaktu=dialoggantiwaktu.findViewById(R.id.UpdateWaktu);

                if(MainActivity.sp!=null){
                    waktu.setText(MainActivity.sp.getSpTime());
                }else{

                }

                UpdateWaktu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        retrofit2.Call<UserData> callUser = MainActivity.userService.updateCSTime(MainActivity.sp.getSpId(),waktu.getText().toString());
                        callUser.enqueue(new Callback<UserData>() {
                            @Override
                            public void onResponse(retrofit2.Call<UserData> call, Response<UserData> response) {
                                UserData u = response.body();
                                if(u!=null){
                                    MainActivity.sp.saveSpTime(waktu.getText().toString());
//                                  loading.setMessage("Update Succsess");
                                    error.setVisibility(INVISIBLE);
                                    try{
                                        success.setVisibility(VISIBLE);
                                        UpdateWaktu.setText("Update");
                                        Thread.sleep(2000);
                                    }
                                    catch(InterruptedException e){}

                                    tvTime.setText(MainActivity.sp.getSpTime());
                                    success.setVisibility(INVISIBLE);
                                }
                            }

                            @Override
                            public void onFailure(retrofit2.Call<UserData> call, Throwable t) {
                                UpdateWaktu.setText("Retry");
                                error.setVisibility(VISIBLE);
                            }
                        });
                    }
                });

                tutup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialoggantiwaktu.dismiss();
                        MainActivity.sp.saveSpTime(waktu.getText().toString());
                        tvTime.setText(MainActivity.sp.getSpTime());
                    }
                });

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
