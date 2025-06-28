package com.example.flowershop.ui.notifications;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flowershop.Prefs;
import com.example.flowershop.R;
import com.example.flowershop.databinding.ItemBoardBinding;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    ItemBoardBinding binding;
    NavController navController;


    private int [] images = new int [] {R.drawable.boardflow,
            R.drawable.boardflow2,
            R.drawable.boardflow3};
    private String [] titles = new String[]  {
            "Welcome to Pearl!",
            "Discover the beauty of nature in every bloom",
            "Join us in celebrating most beautiful creations." };
    @NonNull
    @Override
    public BoardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemBoardBinding
                .inflate(LayoutInflater.from(parent.getContext()),parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull ItemBoardBinding itemView) {
              super(itemView.getRoot());
        }

        public void bind(int position) {
            binding.textBoard.setText(titles[position]);
            binding.imageBoard.setImageResource(images[position]);

            if(position == images.length-1){
                binding.btnStart.setVisibility(View.VISIBLE);
            }else{
                binding.btnStart.setVisibility(View.INVISIBLE);
            }

            binding.btnStart.setOnClickListener(v->{

                new Prefs((Activity)itemView.getContext()).saveBoardState();

                navController = Navigation.findNavController((Activity) itemView.getContext(),
                        R.id.nav_host);
                navController.navigate(R.id.action_navigation_notifications_to_loginFragment);
            });

        }
    }
}
