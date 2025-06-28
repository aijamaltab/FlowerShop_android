package com.example.flowershop.ui.bouquets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flowershop.R;
import com.example.flowershop.models.Flower;

import java.util.List;

public class BouquetsAdapter extends RecyclerView.Adapter<BouquetsAdapter.BouquetViewHolder> {

    private Context context;
    private List<Flower> flowerList;

    public BouquetsAdapter(Context context, List<Flower> flowerList) {
        this.context = context;
        this.flowerList = flowerList;
    }

    public void setFlowers(List<Flower> flowers) {
        this.flowerList = flowers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BouquetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_catalog, parent, false);
        return new BouquetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BouquetViewHolder holder, int position) {
        Flower flower = flowerList.get(position);

        holder.title.setText(flower.getTitle());
        holder.price.setText(flower.getPrice());

        Glide.with(context)
                .load(flower.getPhoto())
                .into(holder.image);

        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle add to cart action
            }
        });
    }

    @Override
    public int getItemCount() {
        return flowerList.size();
    }

    public static class BouquetViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title, price;
        ImageButton addToCart;

        public BouquetViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_popular);
            title = itemView.findViewById(R.id.title_popular);
            price = itemView.findViewById(R.id.price_popular);
            addToCart = itemView.findViewById(R.id.btn_cart);
        }
    }
}
