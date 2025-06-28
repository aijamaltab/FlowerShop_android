package com.example.flowershop.ui.bouquets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flowershop.R;
import com.example.flowershop.models.Flower;
import com.example.flowershop.ui.bouquets.BouquetsAdapter;

import java.util.ArrayList;
import java.util.List;

public class BouquetsFragment extends Fragment {

    private RecyclerView recyclerView;
    private BouquetsAdapter bouquetsAdapter;
    private BouquetsViewModel bouquetsViewModel;
    private static final String TAG = "BouquetsFragment";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bouquets, container, false);

        recyclerView = root.findViewById(R.id.rv_catalog_m);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        bouquetsAdapter = new BouquetsAdapter(getContext(), new ArrayList<>());
        recyclerView.setAdapter(bouquetsAdapter);

        // Initialize ViewModel
        bouquetsViewModel = new ViewModelProvider(this).get(BouquetsViewModel.class);
        observeViewModel();

        ImageView btnBack = root.findViewById(R.id.btn_back);
        ImageView basketBtn = root.findViewById(R.id.basket_btn);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_bouquetsFragment_to_navigation_home);
            }
        });

        basketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_bouquetsFragment_to_basketFragment);
            }
        });

        return root;
    }

    private void observeViewModel() {
        bouquetsViewModel.getFlowerList().observe(getViewLifecycleOwner(), flowers -> {
            if (flowers != null) {
                bouquetsAdapter.setFlowers(flowers);
            } else {
                Toast.makeText(getContext(), "Failed to load flowers", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
