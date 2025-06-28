package com.example.flowershop.ui.basket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flowershop.R;
import com.example.flowershop.models.Flower;

import java.util.List;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.FlowerViewHolder> {

    private List<Flower> basketList;
    private Context context;

    // Constructor
    public BasketAdapter(Context context, List<Flower> basketList) {
        this.context = context;
        this.basketList = basketList;
    }

    // ViewHolder class
    public static class FlowerViewHolder extends RecyclerView.ViewHolder {
        ImageView photoImageView;
        TextView titleTextView;
        TextView priceTextView;
        ImageButton btnRemoveFromBasket;

        public FlowerViewHolder(View itemView) {
            super(itemView);
            photoImageView = itemView.findViewById(R.id.image_popular);
            titleTextView = itemView.findViewById(R.id.title_popular);
            priceTextView = itemView.findViewById(R.id.price_popular);

        }
    }

    @NonNull
    @Override
    public FlowerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_catalog, parent, false);
        return new FlowerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlowerViewHolder holder, int position) {
        Flower flower = basketList.get(position);

        // Bind data to views
        holder.titleTextView.setText(flower.getTitle());
        holder.priceTextView.setText("$" + flower.getPrice());

        // Load photo using Glide
        Glide.with(context)
                .load(flower.getPhoto())
                .placeholder(R.drawable.ic_launcher_background) // Placeholder image
                .into(holder.photoImageView);

        // Handle click events if needed
        holder.btnRemoveFromBasket.setOnClickListener(v -> {
            // Implement logic for removing from basket
            basketList.remove(position);
            notifyDataSetChanged();
            Toast.makeText(context, "Removed from basket: " + flower.getTitle(), Toast.LENGTH_SHORT).show();
            // Add your actual logic here to remove from basket
        });
    }

    @Override
    public int getItemCount() {
        return basketList.size();
    }
}
