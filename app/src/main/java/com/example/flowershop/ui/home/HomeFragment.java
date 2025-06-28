package com.example.flowershop.ui.home;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.airbnb.lottie.LottieAnimationView;
import com.example.flowershop.R;
import com.example.flowershop.databinding.FragmentHomeBinding;
import com.example.flowershop.models.CategoryModel;
import com.example.flowershop.models.PopularItemModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;

    private List<PopularItemModel> popularItemList = new ArrayList<>();
    List<CategoryModel> list_category = new ArrayList<>();
    CategoryAdapter adapter;
    LottieAnimationView lotty_flowers;
    NavController navController;
    private PopularAdapter popularAdapter;
    String userName;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Установка адаптера и менеджера для списка популярных элементов
        popularAdapter = new PopularAdapter(popularItemList);
        binding.rvCatalogPopular.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvCatalogPopular.setAdapter(popularAdapter);


        lotty_flowers = binding.lottyFlowers;
        lotty_flowers.setAnimation(R.raw.flowers2);
        createList();
        // Создание списка популярных элементов

        adapter = new CategoryAdapter();
        adapter.setMain_list(list_category);
        binding.rvCatalogCategory.setAdapter(adapter);



        // Проверка авторизации пользователя
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            // Пользователь не авторизован
            binding.btnLogin.setVisibility(View.VISIBLE);
            binding.btnExit.setVisibility(View.GONE);
        } else {
            // Пользователь авторизован
            binding.btnLogin.setVisibility(View.GONE);
            binding.btnExit.setVisibility(View.VISIBLE);
        }

        return root;
    }
    private void createList() {
        list_category.add(new CategoryModel("Houseplants", R.drawable.hauseplants));
        list_category.add(new CategoryModel("Bouquets", R.drawable.bouqet));
        list_category.add(new CategoryModel("Wedding", R.drawable.wedding));
        list_category.add(new CategoryModel("Houseplants", R.drawable.hauseplants));
        list_category.add(new CategoryModel("Bouquets", R.drawable.bouqet));
        list_category.add(new CategoryModel("Wedding", R.drawable.wedding));


        // Создание списка популярных элементов
        popularItemList.add(new PopularItemModel(R.drawable.bouqet, "Bouquet"));
        popularItemList.add(new PopularItemModel(R.drawable.hauseplants, "Houseplants"));
        popularItemList.add(new PopularItemModel(R.drawable.wedding, "Wedding"));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnLogin.setOnClickListener(v->{
            navController = Navigation.findNavController(requireActivity(),
                    R.id.nav_host);
            navController.navigate(R.id.action_navigation_home_to_loginFragment);
        });
        binding.btnExit.setOnClickListener(v1->{
            binding.btnLogin.setVisibility(View.VISIBLE);
            binding.btnExit.setVisibility(View.INVISIBLE);
//            navController.navigate(R.id.action_navigation_home_to_loginFragment);

        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}