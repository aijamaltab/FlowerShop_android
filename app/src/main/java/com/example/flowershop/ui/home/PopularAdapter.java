package com.example.flowershop.ui.home;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import android.view.LayoutInflater;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flowershop.R;
import com.example.flowershop.models.PopularItemModel;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder> {

    private List<PopularItemModel> popularItemList;

    public PopularAdapter(List<PopularItemModel> popularItemList) {
        this.popularItemList = popularItemList;
    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popular, parent, false);
        return new PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {
        PopularItemModel popularItem = popularItemList.get(position);
        holder.title.setText(popularItem.getItemName());
        holder.image.setImageResource(popularItem.getImageResource());
    }

    @Override
    public int getItemCount() {
        return popularItemList.size();
    }

    public static class PopularViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;

        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_popular);
            title = itemView.findViewById(R.id.title_popular);
        }
    }
}

