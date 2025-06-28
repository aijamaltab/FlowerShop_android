package com.example.flowershop.ui.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.flowershop.R;
import com.example.flowershop.databinding.FragmentLoginBinding;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {

    NavController navController;
    FragmentLoginBinding binding;
    private FirebaseAuth auth;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        auth = FirebaseAuth.getInstance();
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.emailEt.getText().toString();
                String password = binding.passwordEt.getText().toString();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(username, password);
                }
            }
        });

        binding.goToRegister.setOnClickListener(v1 ->{
            navController = Navigation.findNavController(requireActivity(),
                    R.id.nav_host);
            navController.navigate(R.id.action_loginFragment_to_registerFragment);
        });


        return binding.getRoot();
    }

    private void loginUser(String email, String password) {
        // Проверяем, является ли введенный email и пароль администраторскими учетными данными
        if (email.equals("admin@gmail.com") && password.equals("adminadmin")) {
            // Переходим на другую страницу для администратора
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host);
            navController.navigate(R.id.action_loginFragment_to_adminPanelFragment);
        } else {
            // Иначе, проводим аутентификацию через Firebase
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(getActivity(), task -> {
                        if (task.isSuccessful()) {
                            navController = Navigation.findNavController(requireActivity(),
                                    R.id.nav_host);
                            navController.navigate(R.id.action_loginFragment_to_navigation_home);
                        } else {
                            Toast.makeText(getContext(), "Login failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
