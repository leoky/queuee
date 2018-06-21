package com.leoky.queuee.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leoky.queuee.R;
import com.leoky.queuee.api.model.UserList;

import java.util.List;

public class RVList extends RecyclerView.Adapter<RVList.ViewHolder> {
    private List<UserList> userLists;
    private Activity activity;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =(View)LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_list,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public RVList(List<UserList> userLists ,Activity activity){
        this.userLists = userLists;
        this.activity = activity;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvNumb.setText(userLists.get(position).getTitle());
        holder.tvName.setText(userLists.get(position).getBody());
        holder.tvNote.setText(userLists.get(position).getNote());
    }

    @Override
    public int getItemCount() {
        return userLists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvName,tvNumb,tvNote;
        public ViewHolder(View v) {
            super(v);
            tvName = (TextView)v.findViewById(R.id.tvName);
            tvNote = (TextView)v.findViewById(R.id.tvNote);
            tvNumb = (TextView)v.findViewById(R.id.tvNumber);
        }
    }
}
