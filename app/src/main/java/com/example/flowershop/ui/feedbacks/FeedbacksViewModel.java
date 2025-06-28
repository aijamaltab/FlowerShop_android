package com.example.flowershop.ui.feedbacks;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flowershop.models.FeedbackModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
public class FeedbacksViewModel extends ViewModel {
    private MutableLiveData<List<FeedbackModel>> feedbacks;
    private DatabaseReference databaseReference;

    public FeedbacksViewModel() {
        feedbacks = new MutableLiveData<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("feedbacks");
        fetchFeedbacks();
    }

    public LiveData<List<FeedbackModel>> getFeedbacks() {
        return feedbacks;
    }

    private void fetchFeedbacks() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<FeedbackModel> feedbackList = new ArrayList<>();
                for (DataSnapshot feedbackSnapshot : snapshot.getChildren()) {
                    FeedbackModel feedback = feedbackSnapshot.getValue(FeedbackModel.class);
                    if (feedback != null) {
                        feedbackList.add(feedback);
                    }
                }
                feedbacks.setValue(feedbackList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle possible errors.
            }
        });
    }
}
