package com.example.flowershop.ui.basket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flowershop.R;
import com.example.flowershop.models.Flower;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BasketFragment extends Fragment {

    private RecyclerView recyclerView;
    private BasketAdapter basketAdapter;
    private List<Flower> basketList;
    private TextView basketTotalCount;
    private ImageView placeHolder;
    private DatabaseReference basketRef;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_basket, container, false);

        recyclerView = root.findViewById(R.id.rv_basket);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        basketList = new ArrayList<>();
        basketAdapter = new BasketAdapter(getContext(), basketList);
        recyclerView.setAdapter(basketAdapter);

        basketTotalCount = root.findViewById(R.id.basket_total_count);
        placeHolder = root.findViewById(R.id.place_holder);

        // Initialize Firebase Realtime Database reference
        basketRef = FirebaseDatabase.getInstance().getReference().child("basket");

        loadBasketItemsFromFirebase();

        ImageButton btnBack = root.findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Навигация назад через NavController
                navigateBack();
            }
        });

        Button btnPay = root.findViewById(R.id.btn_pay);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Навигация на страницу оплаты
                navigateToPaymentFragment();
            }
        });

        return root;
    }

    private void loadBasketItemsFromFirebase() {
        basketRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                basketList.clear(); // Clear existing list

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Flower flower = snapshot.getValue(Flower.class);
                    basketList.add(flower);
                }

                // Update RecyclerView
                basketAdapter.notifyDataSetChanged();

                // Update total count
                double total = calculateTotalPrice();
                basketTotalCount.setText(String.format("%.2f", total));

                // Update visibility of placeholder based on basketList size
                if (basketList.isEmpty()) {
                    placeHolder.setVisibility(View.VISIBLE);
                } else {
                    placeHolder.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors
                Toast.makeText(getContext(), "Failed to load basket items: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private double calculateTotalPrice() {
        double total = 0;
        for (Flower flower : basketList) {
            total += Double.parseDouble(flower.getPrice());
        }
        return total;
    }

    private void navigateToPaymentFragment() {
        // Используйте Navigation Component для перехода на PaymentFragment
        NavHostFragment.findNavController(this).navigate(R.id.action_basketFragment_to_paymentFragment);
    }

    private void navigateBack() {
        // Используйте Navigation Component для навигации назад
        NavHostFragment.findNavController(this).navigateUp();
    }
}
