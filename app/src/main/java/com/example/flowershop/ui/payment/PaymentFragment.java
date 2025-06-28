package com.example.flowershop.ui.payment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.flowershop.R;
import com.example.flowershop.databinding.FragmentPaymentBinding;


public class PaymentFragment extends Fragment {

   FragmentPaymentBinding binding;
   NavController navController;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding =FragmentPaymentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.btnBack.setOnClickListener(v ->{
            navController = Navigation.findNavController(requireActivity(),
                    R.id.nav_host);
            navController.navigate(R.id.action_paymentFragment_to_basketFragment);
        });

        binding.cardVisa.setOnClickListener(v->{
            Intent myIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/detaills?id=net.kicb.ibankprod"));
            startActivity(myIntent);
        });
        binding.cardPayPal.setOnClickListener(v->{
            Intent myIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=kg.bta.mobilebank2"));
            startActivity(myIntent);
        });
        binding.cardMbank.setOnClickListener(v->{
            Intent myIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=com.maanavan.mb_kyrgyzstan"));
            startActivity(myIntent);
        });
        binding.cardO.setOnClickListener(v->{
            Intent myIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=kg.o.nurtelecom"));
            startActivity(myIntent);
        });
    }
}