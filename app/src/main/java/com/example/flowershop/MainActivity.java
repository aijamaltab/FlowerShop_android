package com.example.flowershop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.flowershop.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

   ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.feedbacksFragment, R.id.basketFragment, R.id.contactFragment)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        // Проверяем состояние авторизации при запуске приложения
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            // Пользователь авторизован, переходим на home_fragment
            navController.navigate(R.id.navigation_home);
        } else {
            // Пользователь не авторизован, переходим на login_fragment
            navController.navigate(R.id.navigation_home);
        }



    Prefs prefs = new Prefs(this);
        if(!prefs.isShow()){
            navController.navigate(R.id.navigation_notifications);
        } else{
            navController.navigate(R.id.navigation_home);
        }

        //-------------------------------------СКРЫТИЕ НАВБАРА(navView)-------------------------------------

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                ArrayList<Integer> hideNavViewDestinations = new ArrayList<>();
                hideNavViewDestinations.add(R.id.loginFragment);
                hideNavViewDestinations.add(R.id.registerFragment);
                hideNavViewDestinations.add(R.id.navigation_notifications);
                hideNavViewDestinations.add(R.id.adminPanelFragment);


                // Проверяем, является ли текущая целевая точка одной из тех, где нужно скрывать навбар
                if (hideNavViewDestinations.contains(navDestination.getId())) {
                    navView.setVisibility(View.GONE); // Скрываем навбар
                } else {
                    navView.setVisibility(View.VISIBLE); // Показываем навбар для остальных страниц
                }
            }
        });
//------------------------------------------------------------------------------
    }




}