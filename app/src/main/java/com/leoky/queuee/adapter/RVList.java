package com.leoky.queuee.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leoky.queuee.R;
import com.leoky.queuee.api.model.Queue;

import java.util.List;

public class RVList extends RecyclerView.Adapter<RVList.ViewHolder> {
    private List<Queue> queues;
    private ClickListener clickListener = null;
    private Activity activity;

    public interface ClickListener{
         void itemClicked(View view, int position);
    }

    public void setClickListener(ClickListener clicklistener) {
        this.clickListener = clicklistener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_list,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public RVList(List<Queue> queues ,Activity activity){
        this.queues = queues;
        this.activity = activity;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            if(queues.get(position).getStatus().equals("Queue")) {
            holder.tvNumb.setText(queues.get(position).getOrder_no());
            holder.tvName.setText(queues.get(position).getPatient().getName());
            holder.tvNote.setText(queues.get(position).getNote());
        }
    }

    @Override
    public int getItemCount() {
        return queues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tvName,tvNumb,tvNote;
        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            tvName = (TextView)v.findViewById(R.id.tvName);
            tvNote = (TextView)v.findViewById(R.id.tvNote);
            tvNumb = (TextView)v.findViewById(R.id.tvNumber);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                clickListener.itemClicked(view, getAdapterPosition());
            }
        }
    }
}
