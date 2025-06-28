package com.example.flowershop.remote_data;

import com.example.flowershop.models.FeedbackModel;
import com.example.flowershop.models.Flower;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {


    @GET("flowers.json")
    Call<List<Flower>> getStoreProducts();

//    --------------------
//void OnFeedbackReceivedListener(List<FeedbackModel> feedbacks);
//    --------------------
}
