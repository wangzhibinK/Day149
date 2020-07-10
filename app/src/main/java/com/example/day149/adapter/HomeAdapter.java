package com.example.day149.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.day149.R;
import com.example.day149.bean.ProjectBean;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private ArrayList<ProjectBean.DataBean.DatasBean> list;
    private Context context;

    public HomeAdapter(ArrayList<ProjectBean.DataBean.DatasBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProjectBean.DataBean.DatasBean datasBean = list.get(position);
        Glide.with(context).load(datasBean.getEnvelopePic()).into(holder.iv_list_img);
        holder.tv_list_name.setText(datasBean.getDesc());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_list_img;
        private TextView tv_list_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             iv_list_img = itemView.findViewById(R.id.iv_list_img);
             tv_list_name = itemView.findViewById(R.id.tv_list_name);
        }


    }
}
