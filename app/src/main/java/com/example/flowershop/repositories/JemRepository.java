//package com.example.flowershop.repositories;
//
//import android.util.Log;
//
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//
//import com.example.flowershop.models.FeedbackModel;
//import com.example.flowershop.remote_data.RetrofitClient;
//
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class JemRepository {
//    final MutableLiveData<List<FeedbackModel>>data=new MutableLiveData<>();
//
//    public LiveData<List<FeedbackModel>> getDashJeminyList(){
//        Call<List<FeedbackModel>> apiCall= RetrofitClient.getInstance().getApi().getStoreProducts();
//        apiCall.enqueue(new Callback<List<FeedbackModel>>() {
//            @Override
//            public void onResponse(Call<List<FeedbackModel>> call, Response<List<FeedbackModel>> response) {
//                if(response.body()!=null){
//                    data.postValue(response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<FeedbackModel>> call, Throwable throwable) {
//                Log.e("TAG","NO DATA"+throwable.getLocalizedMessage());
//                data.postValue(null);
//            }
//        });
//        return data;
//    }
//}