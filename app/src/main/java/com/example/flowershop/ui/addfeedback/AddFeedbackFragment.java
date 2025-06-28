package com.example.flowershop.ui.addfeedback;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.flowershop.R;
import com.example.flowershop.databinding.FragmentAddFeedbackBinding;
import com.example.flowershop.models.FeedbackModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class AddFeedbackFragment extends Fragment {
    FirebaseDatabase database;
    DatabaseReference feedbackRef;
    String key;

    FragmentAddFeedbackBinding binding;
    NavController navController;
//    String feedback;
//    String name;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddFeedbackBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.backBtn.setOnClickListener(v ->{
            navController = Navigation.findNavController(requireActivity(),
                    R.id.nav_host);
            navController.navigate(R.id.action_addFeedbackFragment_to_feedbacksFragment);
        });

        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                //get text
                String feedback = binding.feedbackEditText.getText().toString();
                String name = binding.nameEditText.getText().toString();

                //check if empty
                if(feedback.isEmpty()){
                    binding.feedbackEditText.setError("Cannot be empty");
                    return;
                }
                if(name.isEmpty()){
                    binding.nameEditText.setError("Cannot be empty");
                    return;
                }

                // add to database
                addFeedbackToDB(feedback,name);

            }
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    private void addFeedbackToDB(String feedback, String name) {
//        FeedbackModel feedbacks =  new FeedbackModel(feedbackText, name);
        //create a hashmap
        HashMap<String, String> feedbackHashmap = new HashMap<>();
        feedbackHashmap.put("feedback", feedback);
        feedbackHashmap.put("user", name);

        //instantiate db connection
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://flowshop-22a66-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference feedbackRef =   database.getReference(" feedbacks");
        String key = feedbackRef.push().getKey();
        feedbackHashmap.put("key", key);
        feedbackRef.child(key).setValue(feedbackHashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(requireContext(), "Added", Toast.LENGTH_SHORT).show();
                binding.feedbackEditText.getText().clear();
                binding.nameEditText.getText().clear();
            }
        });
        //write to db
    }

}