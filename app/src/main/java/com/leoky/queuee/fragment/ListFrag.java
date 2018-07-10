package com.leoky.queuee.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
import com.leoky.queuee.api.model.UserList;
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

    private List<Queue> lists;
    private String numNow;

    public RecyclerView recyclerView;
    private Toolbar toolbar;
    private LinearLayout llnow;
    private TextView tvName,tvNote,tvTBNum,tvTBTotalList;
    private ImageView imgNow;
    private ProgressBar pb;

    private RVList myAdapter;

    public ListFrag() {
        // Required empty public constructor
    }
//    private UserList[]userLists = new UserList[]{
//            new UserList("22","Leonardy","gak bisa tidur",8),
//            new UserList("23","Wilvin","Sakit hati sudah 2 tahun tak kunjung sembuh.",8),
//            new UserList("24","Adinda","demam",8),
//            new UserList("25","David","pilek",8),
//            new UserList("26","Thafa","sakit perut",8),
//            new UserList("27","Audian","Berat badan bertambah meski hanya bernafas",8),
//            new UserList("28","Audian","Berat badan bertambah meski hanya bernafas",8),
//            new UserList("29","Audian","Berat badan bertambah meski hanya bernafas",8),
//            new UserList("30","Audian","Berat badan bertambah meski hanya bernafas",8),
//            new UserList("31","Audian","Berat badan bertambah meski hanya bernafas",8),
//            new UserList("32","Audian","Berat badan bertambah meski hanya bernafas",8),
//            new UserList("33","Audian","Berat badan bertambah meski hanya bernafas",8),
//            new UserList("34","Audian","Berat badan bertambah meski hanya bernafas",8),
//            new UserList("35","Audian","Berat badan bertambah meski hanya bernafas",8),
//            new UserList("36","Audian","Berat badan bertambah meski hanya bernafas",8),
//            new UserList("37","Audian","Berat badan bertambah meski hanya bernafas",8),
//            new UserList("38","Audian","Berat badan bertambah meski hanya bernafas",8)
//    };
    public static ListFrag newInstance() {
        
        Bundle args = new Bundle();
        
        ListFrag fragment = new ListFrag();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = v.findViewById(R.id.rvList);
        toolbar = v.findViewById(R.id.toolbar);
        llnow = v.findViewById(R.id.llnow);
        imgNow = v.findViewById(R.id.imgNow);
        tvName= v.findViewById(R.id.tvName);
        tvNote = v.findViewById(R.id.tvNote);
        tvTBNum = toolbar.findViewById(R.id.tvTBNum);
        tvTBTotalList = toolbar.findViewById(R.id.tvTBTotalList);
        pb = v.findViewById(R.id.pb);

        // on process queue now
        getQueueList();

        //click listner
        v.findViewById(R.id.imgRefresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getQueueList();
            }
        });
        v.findViewById(R.id.btnDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                System.out.println("NO "+lists.size());
//                if(lists.size()>0){
//                    lists.remove(0);
//                    updateView();
//                }else{
//                    lists.clear();
//                }
//                myAdapter.notifyDataSetChanged();
            }
        });
        v.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                lists.remove(0);
//                updateView();
//                myAdapter.notifyDataSetChanged();
            }
        });
        llnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentList(0);
            }
        });
        return v;
    }

    @Override
    public void itemClicked(View view, int position) {
       intentList(position);
    }
    private void updateView(){
        tvTBTotalList.setText(numNow);
        if(lists.get(0).getStatus().equals("Progress")){
            tvTBNum.setText(lists.get(0).getOrder_no());
            tvName.setText(lists.get(0).getPatient().getName());
            tvNote.setText(lists.get(0).getNote());
            Picasso.get().load(R.drawable.profile).into(imgNow);
        }
    }
    private void getQueueList(){
        Call<RepoQueue> call = MainActivity.userService.getQueueList();
        call.enqueue(new Callback<RepoQueue>() {
            @Override
            public void onResponse(Call<RepoQueue> call, Response<RepoQueue> response) {
                RepoQueue u = response.body();
                lists = u.getQueue();
                numNow = u.getTotal_queue();
                updateView();
                setRecyclerView();
                pb.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<RepoQueue> call, Throwable t) {
                System.out.println("errror" + t);
            }
        });
    }
    private void setRecyclerView(){
        //recycle view
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        //use linear layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //specifiy an apdapter
        myAdapter = new RVList(lists,getActivity());
        recyclerView.setAdapter(myAdapter);
        myAdapter.setClickListener((RVList.ClickListener) this);
    }
    public void intentList(int position){
        Intent intent = new Intent(getActivity(), ListDetailActivity.class);
        intent.putExtra(ListDetailActivity.LD_NAME,lists.get(position).getPatient().getName());
        intent.putExtra(ListDetailActivity.LD_NOTE,lists.get(position).getNote());
        intent.putExtra(ListDetailActivity.LD_NUM,lists.get(position).getOrder_no());
        intent.putExtra(ListDetailActivity.LD_STATUS,lists.get(position).getStatus());
        getActivity().startActivity(intent);
    }
}
