package com.example.flowershop.ui.bouquets;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flowershop.models.Flower;
import com.example.flowershop.remote_data.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BouquetsViewModel extends ViewModel {

    private MutableLiveData<List<Flower>> flowerList;

    public LiveData<List<Flower>> getFlowerList() {
        if (flowerList == null) {
            flowerList = new MutableLiveData<>();
            loadFlowers(); // Load data initially
        }
        return flowerList;
    }

    private void loadFlowers() {
        RetrofitClient.getInstance().getApi().getStoreProducts().enqueue(new Callback<List<Flower>>() {
            @Override
            public void onResponse(Call<List<Flower>> call, Response<List<Flower>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    flowerList.setValue(response.body());
                } else {
                    // Handle error
                    // For example:
                    flowerList.setValue(null); // Reset the value or handle differently
                }
            }

            @Override
            public void onFailure(Call<List<Flower>> call, Throwable t) {
                // Handle failure
                // For example:
                flowerList.setValue(null); // Reset the value or handle differently
            }
        });
    }
}
