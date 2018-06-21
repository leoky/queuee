package com.leoky.queuee.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leoky.queuee.R;
import com.leoky.queuee.adapter.RVList;
import com.leoky.queuee.api.model.UserList;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFrag extends Fragment {

    private List<UserList> lists;
    public RecyclerView recyclerView;


    public ListFrag() {
        // Required empty public constructor
    }
    private UserList[]userLists = new UserList[]{
            new UserList("22","Leonardy","gak bisa tidur",8),
            new UserList("23","Wilvin","Sakit hati sudah 2 tahun tak kunjung sembuh.",8),
            new UserList("24","Adinda","demam",8),
            new UserList("25","David","pilek",8),
            new UserList("26","Thafa","sakit perut",8),
            new UserList("27","Audian","Berat badan bertambah meski hanya bernafas",8)
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
        recyclerView = (RecyclerView)v.findViewById(R.id.rvList);

        recyclerView.setHasFixedSize(true);
        //use linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //specifiy an apdapter(see also next exzmple_)
        RVList myAdapter = new RVList(lists,getActivity());
        recyclerView.setAdapter(myAdapter);
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
}
