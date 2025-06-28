package com.example.flowershop.ui.registration;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.flowershop.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.flowershop.databinding.FragmentRegisterBinding;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterFragment extends Fragment {

    FragmentRegisterBinding binding;
    NavController navController;
    private FirebaseAuth auth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        auth = FirebaseAuth.getInstance();
        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.usernameEt.getText().toString();
                String password = binding.passwordEt.getText().toString();
                String email = binding.emailEt.getText().toString();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(email)) {
                    Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(email, password);
                }
            }
        });

        binding.backBtn.setOnClickListener(v2-> {
            navController = Navigation.findNavController(requireActivity(),
                    R.id.nav_host);
            navController.navigate(R.id.action_registerFragment_to_loginFragment);


        });

        return binding.getRoot();
    }

    private void registerUser(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getContext(), "Registration successful", Toast.LENGTH_SHORT).show();
                        navController = Navigation.findNavController(requireActivity(),
                                R.id.nav_host);
                        navController.navigate(R.id.action_registerFragment_to_loginFragment);
                    } else {
                        Toast.makeText(getContext(), "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
