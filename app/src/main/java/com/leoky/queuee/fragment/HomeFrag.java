package com.leoky.queuee.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.leoky.queuee.R;
import com.leoky.queuee.activity.LoginActivity;
import com.leoky.queuee.activity.MainActivity;
import com.leoky.queuee.api.model.UserData;
import com.leoky.queuee.session.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFrag extends Fragment {
    private SwitchCompat sw;
    private Toolbar toolbar;
    private TextView tvName,tvStatus;
    ProgressDialog loading;


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
        tvName = v.findViewById(R.id.tvName);
        tvStatus = v.findViewById(R.id.tvStatus);

        loading = ProgressDialog.show(getContext(), null, "Please wait", true, false);
        updateView();
        updateData();

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                loading = ProgressDialog.show(getContext(), null, "Please wait", true, false);
                updateData();
            }
        });

        return v;
    }
    private void updateView(){
        sw.setChecked((MainActivity.sp.getSpCStatus().equals("Open")? true : false));
        tvName.setText(MainActivity.sp.getSpName());
        tvStatus.setText((sw.isChecked()? getString(R.string.online) : getString(R.string.offline)));
        tvStatus.setBackgroundColor((sw.isChecked()? getResources().getColor(R.color.colorBtnEnable) : getResources().getColor(R.color.colorNegative)));
    }

    private void updateData(){
        String status = (sw.isChecked()? "Open" : "Close");
        Call<UserData> d = MainActivity.userService.updateCStatus(MainActivity.sp.getSpId(),status);
        d.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                UserData u = response.body();
                if(u!=null){
                    MainActivity.sp.saveSpCStatus(u.getClinic().getStatus());
                    updateView();
                    loading.dismiss();
                }else{
                    System.out.println("nulllll ");
                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                System.out.println("erorr "+ t );
            }
        });
    }

}
