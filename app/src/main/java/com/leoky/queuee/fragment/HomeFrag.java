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
import com.leoky.queuee.activity.MainActivity;
import com.leoky.queuee.api.model.Home;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFrag extends Fragment {
    private SwitchCompat sw;
    private Toolbar toolbar;
    private TextView tvName,tvStatus,tvTotalQueue,tvTotalComplete,tvQueue,tvComplete;
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
        tvTotalComplete = v.findViewById(R.id.tvCompleteNum);
        tvTotalQueue = v.findViewById(R.id.tvTotalQueNum);
        tvQueue = v.findViewById(R.id.tvTotalQue);
        tvComplete = v.findViewById(R.id.tvComplete);

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
        tvTotalComplete.setVisibility((MainActivity.sp.getSpCStatus().equals("Open")? View.VISIBLE : View.GONE));
        tvTotalQueue.setVisibility((MainActivity.sp.getSpCStatus().equals("Open")? View.VISIBLE : View.GONE));
        tvQueue.setVisibility((MainActivity.sp.getSpCStatus().equals("Open")? View.VISIBLE : View.GONE));
        tvComplete.setVisibility((MainActivity.sp.getSpCStatus().equals("Open")? View.VISIBLE : View.GONE));
        sw.setChecked((MainActivity.sp.getSpCStatus().equals("Open")? true : false));
        tvName.setText(MainActivity.sp.getSpName());
        tvStatus.setText((sw.isChecked()? getString(R.string.online) : getString(R.string.offline)));
        tvStatus.setBackgroundColor((sw.isChecked()? getResources().getColor(R.color.colorBtnEnable) : getResources().getColor(R.color.colorNegative)));
    }

    private void updateData(){
        String status = (sw.isChecked()? "Open" : "Close");
        Call<Home> d = MainActivity.userService.updateCStatus(MainActivity.sp.getSpId(),status);
        d.enqueue(new Callback<Home>() {
            @Override
            public void onResponse(Call<Home> call, Response<Home> response) {
                Home u = response.body();
                if(u!=null){
                    MainActivity.sp.saveSpCStatus(u.getStatus());
                    updateView();
                    tvTotalComplete.setText(u.getTotal_complete());
                    tvTotalQueue.setText(u.getTotal_queue());
                    loading.dismiss();
                }else{
                    System.out.println("nulllll ");
                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Home> call, Throwable t) {
                System.out.println("erorr "+ t );
                loading.dismiss();
            }
        });
    }

}
