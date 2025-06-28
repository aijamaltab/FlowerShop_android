package com.example.flowershop.ui.home;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flowershop.R;
import com.example.flowershop.databinding.ItemCategoryBinding;
import com.example.flowershop.models.CategoryModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    List<CategoryModel> main_list = new ArrayList<>();
    ItemCategoryBinding binding;
    NavController navController;

    public void setMain_list(List<CategoryModel> main_list) {
        this.main_list = main_list;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemCategoryBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        holder.onBind(main_list.get(position));
    }

    @Override
    public int getItemCount() {
        return main_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemCategoryBinding binding;

        public ViewHolder(@NonNull ItemCategoryBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
        public void onBind(CategoryModel categoryModel) {

            binding.titleCategory.setText(categoryModel.getTitle());
            binding.imageCategory.setImageResource(categoryModel.getImage());



            itemView.setOnClickListener(v-> {

                switch (categoryModel.getTitle()) {
                    case "Bouquets":
                        navController = Navigation.findNavController((Activity) itemView.getContext(),
                                R.id.nav_host);
                        navController.navigate(R.id.action_navigation_home_to_bouquetsFragment);
                        break;

                    case "Houseplants":
                        navController = Navigation.findNavController((Activity) itemView.getContext(),
                                R.id.nav_host);
                        navController.navigate(R.id.action_navigation_home_to_houseplantsFragment);

                        break;

                    case "Wedding":
                        navController = Navigation.findNavController((Activity) itemView.getContext(),
                                R.id.nav_host);
                        navController.navigate(R.id.action_navigation_home_to_weddingFragment);

                        break;

                    default:

                }


            });

        }

    }
}