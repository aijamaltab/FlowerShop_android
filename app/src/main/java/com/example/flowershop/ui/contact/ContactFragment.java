package com.example.flowershop.ui.contact;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.flowershop.R;
import com.example.flowershop.databinding.FragmentContactBinding;
import com.example.flowershop.databinding.FragmentPaymentBinding;

public class ContactFragment extends Fragment {
    NavController navController;
    FragmentContactBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentContactBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.whatsapp.setOnClickListener(v->{
            Intent myIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://wa.me/996550542277"));
            startActivity(myIntent);
        });
        binding.instagram.setOnClickListener(v->{
            Intent myIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/akapella.flowers?igsh=ZmNhcHRjazl0eWpi"));
            startActivity(myIntent);
        });
        binding.telegram.setOnClickListener(v->{
            Intent myIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://t.me/996550542277"));
            startActivity(myIntent);
        });

    }
}