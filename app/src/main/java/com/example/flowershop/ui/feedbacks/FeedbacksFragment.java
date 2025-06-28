package com.example.flowershop.ui.feedbacks;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.flowershop.R;
import com.example.flowershop.databinding.FragmentFeedbacksBinding;
import com.example.flowershop.models.FeedbackModel;
import com.example.flowershop.remote_data.FeedbackListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FeedbacksFragment extends Fragment implements FeedbackListener {

    private RecyclerView recyclerView;
    private FeedbackAdapter adapter;
    private DatabaseReference feedbackRef;
    FragmentFeedbacksBinding binding;
    NavController navController;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFeedbacksBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.floatingActionButton.setOnClickListener(v ->{
            navController = Navigation.findNavController(requireActivity(),
                    R.id.nav_host);
            navController.navigate(R.id.action_feedbacksFragment_to_addFeedbackFragment);
        });
        recyclerView = view.findViewById(R.id.feedbacks_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new FeedbackAdapter(this);
        recyclerView.setAdapter(adapter);

        // Initialize Firebase Database
        feedbackRef = FirebaseDatabase.getInstance("https://flowshop-22a66-default-rtdb.europe-west1.firebasedatabase.app/")
                .getReference(" feedbacks");

        // Read from the database
        feedbackRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<FeedbackModel> feedbacks = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    FeedbackModel feedback = snapshot.getValue(FeedbackModel.class);
                    if (feedback != null) {
                        feedbacks.add(feedback);
                    }
                }
                adapter.setFeedbacks(feedbacks);

                // Log the number of feedbacks fetched
                Log.d("FeedbacksFragment", "Number of feedbacks: " + feedbacks.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(requireContext(), "Failed to load feedbacks.", Toast.LENGTH_SHORT).show();
                Log.e("FeedbacksFragment", "Database error: " + error.getMessage());
            }
        });
    }

    @Override
    public void onFeedbackClicked(FeedbackModel feedback) {
        // Handle feedback item click
        Toast.makeText(requireContext(), "Clicked: " + feedback.getFeedback(), Toast.LENGTH_SHORT).show();
    }
}
