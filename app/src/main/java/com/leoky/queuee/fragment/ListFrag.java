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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.leoky.queuee.R;
import com.leoky.queuee.activity.ListDetailActivity;
import com.leoky.queuee.activity.MainActivity;
import com.leoky.queuee.adapter.RVList;
import com.leoky.queuee.api.model.UserList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFrag extends Fragment implements RVList.ClickListener{

    private List<UserList> lists;

    public RecyclerView recyclerView;
    private Toolbar toolbar;
    private LinearLayout llnow;
    private TextView tvName,tvNote,tvTBNum;
    private ImageView imgNow;


    public ListFrag() {
        // Required empty public constructor
    }
    private UserList[]userLists = new UserList[]{
            new UserList("22","Leonardy","gak bisa tidur",8),
            new UserList("23","Wilvin","Sakit hati sudah 2 tahun tak kunjung sembuh.",8),
            new UserList("24","Adinda","demam",8),
            new UserList("25","David","pilek",8),
            new UserList("26","Thafa","sakit perut",8),
            new UserList("27","Audian","Berat badan bertambah meski hanya bernafas",8),
            new UserList("28","Audian","Berat badan bertambah meski hanya bernafas",8),
            new UserList("29","Audian","Berat badan bertambah meski hanya bernafas",8),
            new UserList("30","Audian","Berat badan bertambah meski hanya bernafas",8),
            new UserList("31","Audian","Berat badan bertambah meski hanya bernafas",8),
            new UserList("32","Audian","Berat badan bertambah meski hanya bernafas",8),
            new UserList("33","Audian","Berat badan bertambah meski hanya bernafas",8),
            new UserList("34","Audian","Berat badan bertambah meski hanya bernafas",8),
            new UserList("35","Audian","Berat badan bertambah meski hanya bernafas",8),
            new UserList("36","Audian","Berat badan bertambah meski hanya bernafas",8),
            new UserList("37","Audian","Berat badan bertambah meski hanya bernafas",8),
            new UserList("38","Audian","Berat badan bertambah meski hanya bernafas",8)
    };
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
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = v.findViewById(R.id.rvList);
        toolbar = v.findViewById(R.id.toolbar);
        llnow = v.findViewById(R.id.llnow);
        imgNow = v.findViewById(R.id.imgNow);
        tvName= v.findViewById(R.id.tvName);
        tvNote = v.findViewById(R.id.tvNote);
        tvTBNum = toolbar.findViewById(R.id.tvTBNum);

        // on process queue now
        tvTBNum.setText(lists.get(0).getTitle().toString());
        tvName.setText(lists.get(0).getBody().toString());
        tvNote.setText(lists.get(0).getNote().toString());
        Picasso.get().load(R.drawable.profile).into(imgNow);

       tvTBNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        llnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ListDetailActivity.class);
                intent.putExtra(ListDetailActivity.LD_NAME,lists.get(0).getBody());
                intent.putExtra(ListDetailActivity.LD_NOTE,lists.get(0).getNote());
                intent.putExtra(ListDetailActivity.LD_NUM,lists.get(0).getTitle());
                getActivity().startActivity(intent);
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        //use linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        //specifiy an apdapter
        RVList myAdapter = new RVList(lists,getActivity());
        recyclerView.setAdapter(myAdapter);
        myAdapter.setClickListener((RVList.ClickListener) this);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lists = new ArrayList<UserList>();
        for(UserList u : userLists){
            lists.add(u);
        }

    }

    @Override
    public void itemClicked(View view, int position) {
        Intent intent = new Intent(getActivity(), ListDetailActivity.class);
        intent.putExtra(ListDetailActivity.LD_NAME,lists.get(position).getBody());
        intent.putExtra(ListDetailActivity.LD_NOTE,lists.get(position).getNote());
        intent.putExtra(ListDetailActivity.LD_NUM,lists.get(position).getTitle());
        getActivity().startActivity(intent);
    }
}
