package com.leoky.queuee.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.leoky.queuee.R;
import com.leoky.queuee.activity.ListDetailActivity;
import com.leoky.queuee.activity.MainActivity;
import com.leoky.queuee.adapter.RVList;
import com.leoky.queuee.api.model.Queue;
import com.leoky.queuee.api.model.RepoQueue;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFrag extends Fragment implements RVList.ClickListener{

    private List<Queue> lists = new ArrayList<>();
    private List<Queue> queueNow = new ArrayList<>();
    private String numNow;

    public RecyclerView recyclerView;
    private Toolbar toolbar;
    private LinearLayout llnow;
    private TextView tvName,tvNote,tvTBNum,tvTBTotalList;
    private ImageView imgNow;
    private ProgressBar pb;
    private ProgressDialog loading;

    private RVList myAdapter;

    public ListFrag() {
        // Required empty public constructor
    }

    public static ListFrag newInstance() {
        
        Bundle args = new Bundle();
        
        ListFrag fragment = new ListFrag();
        fragment.setArguments(args);
        return fragment;
    }
    private void initID(View v){
        recyclerView = v.findViewById(R.id.rvList);
        toolbar = v.findViewById(R.id.toolbar);
        llnow = v.findViewById(R.id.llnow);
        imgNow = v.findViewById(R.id.imgNow);
        tvName= v.findViewById(R.id.tvName);
        tvNote = v.findViewById(R.id.tvNote);
        tvTBNum = toolbar.findViewById(R.id.tvTBNum);
        tvTBTotalList = toolbar.findViewById(R.id.tvTBTotalList);
        pb = v.findViewById(R.id.pb);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        initID(v);
        // on process queue now
        getQueueList(true);

        //click listner
        v.findViewById(R.id.imgRefresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pb.setVisibility(View.VISIBLE);
                getQueueList(false);
            }
        });
        v.findViewById(R.id.btnDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading = ProgressDialog.show(getContext(), null, "Please wait", true, false);
                doneQueue();
            }
        });
        v.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading = ProgressDialog.show(getContext(), null, "Please wait", true, false);
                cancelQueue();
            }
        });
        llnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentList(queueNow,0);
            }
        });
        return v;
    }

    @Override
    public void itemClicked(View view, int position) {
       intentList(lists,position);
    }
    private void updateView(){
        tvTBTotalList.setText(numNow);
        tvTBNum.setText(queueNow.get(0).getOrder_no());
        tvName.setText(queueNow.get(0).getPatient().getName());
        tvNote.setText(queueNow.get(0).getNote());
        Picasso.get().load(R.drawable.profile).into(imgNow);
    }
    private void getQueueList(final boolean first){
        Call<RepoQueue> call = MainActivity.userService.getQueueList(MainActivity.sp.getSpId());
        call.enqueue(new Callback<RepoQueue>() {
            @Override
            public void onResponse(Call<RepoQueue> call, Response<RepoQueue> response) {
                updateData(first,response.body());
                pb.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<RepoQueue> call, Throwable t) {
                Toast.makeText(getContext(),""+t,Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void doneQueue(){
        Call<RepoQueue> c = MainActivity.userService.queueDone(queueNow.get(0).get_id(),MainActivity.sp.getSpId(),"");
        c.enqueue(new Callback<RepoQueue>() {
            @Override
            public void onResponse(Call<RepoQueue> call, Response<RepoQueue> response) {
                updateData(false,response.body());
                loading.dismiss();
            }

            @Override
            public void onFailure(Call<RepoQueue> call, Throwable t) {
                Toast.makeText(getContext(),""+t,Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void cancelQueue(){
        Call<RepoQueue> c = MainActivity.userService.queueCancel(queueNow.get(0).get_id(),MainActivity.sp.getSpId(),"");
        c.enqueue(new Callback<RepoQueue>() {
            @Override
            public void onResponse(Call<RepoQueue> call, Response<RepoQueue> response) {
                updateData(false,response.body());
                loading.dismiss();
            }

            @Override
            public void onFailure(Call<RepoQueue> call, Throwable t) {
                Toast.makeText(getContext(),""+t,Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initRecyclerView(){
        //recycle view
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        //use linear layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // adapter
        myAdapter = new RVList(lists,getActivity());
        recyclerView.setAdapter(myAdapter);
        myAdapter.setClickListener((RVList.ClickListener) this);
    }
    private void updateData(boolean first,RepoQueue u){
        if(u.getQueue().size()!=0){
            if(lists.size()>0){
                lists.clear();
            }
            if(queueNow.size() >0){
                queueNow.clear();
            }
            lists.addAll(u.getQueue());
            numNow = u.getTotal_queue();
            queueNow.add(lists.get(0));
            updateView();
            lists.remove(0);
            if(first)initRecyclerView();
            else{
                myAdapter.notifyDataSetChanged();
            }
        }
    }
    public void intentList(List<Queue> lists,int position){
        Intent intent = new Intent(getActivity(), ListDetailActivity.class);
        intent.putExtra(ListDetailActivity.LD_NAME,lists.get(position).getPatient().getName());
        intent.putExtra(ListDetailActivity.LD_NOTE,lists.get(position).getNote());
        intent.putExtra(ListDetailActivity.LD_NUM,lists.get(position).getOrder_no());
        intent.putExtra(ListDetailActivity.LD_STATUS,lists.get(position).getStatus());
        getActivity().startActivity(intent);
    }
}
