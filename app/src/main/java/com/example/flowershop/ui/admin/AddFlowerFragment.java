package com.example.flowershop.ui.admin;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.flowershop.R;
import com.example.flowershop.databinding.FragmentAddFeedbackBinding;
import com.example.flowershop.databinding.FragmentAddFlowerBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;

public class AddFlowerFragment extends Fragment {
    FirebaseDatabase database;
    DatabaseReference feedbackRef;
    String key;

    FragmentAddFlowerBinding binding;
    NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddFlowerBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.backBtn.setOnClickListener(v ->{
            navController = Navigation.findNavController(requireActivity(),
                    R.id.nav_host);
            navController.navigate(R.id.action_addFlowerFragment_to_adminPanelFragment);
        });



        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                //get text
                String name = binding.editTextName.getText().toString();
                String price = binding.editTextPrice.getText().toString();
                String photo = binding.editTextPhoto.getText().toString();
                String description = binding.editTextDescription.getText().toString();


                //check if empty
                if(name.isEmpty()){
                    binding.editTextName.setError("Cannot be empty");
                    return;
                }
                if(price.isEmpty()){
                    binding.editTextPrice.setError("Cannot be empty");
                    return;
                }
                if(photo.isEmpty()){
                    binding.editTextPhoto.setError("Cannot be empty");
                    return;
                }
                if(description.isEmpty()){
                    binding.editTextDescription.setError("Cannot be empty");
                    return;
                }

                // add to database
                addFeedbackToDB(name, price,photo,description);

            }
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    private void addFeedbackToDB(String name, String price, String photo, String description) {
        //create a hashmap
        HashMap<String, String> flowerHashmap = new HashMap<>();
        flowerHashmap.put("user", name);
        flowerHashmap.put("price", price);
        flowerHashmap.put("photo", photo);
        flowerHashmap.put("description", description);


        //instantiate db connection
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://flowshop-22a66-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference feedbackRef = database.getReference("flowers");
        String key = feedbackRef.push().getKey();
        flowerHashmap.put("key", key);
        feedbackRef.child(key).setValue(flowerHashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(requireContext(), "Added", Toast.LENGTH_SHORT).show();
                binding.editTextName.getText().clear();
                binding.editTextPrice.getText().clear();
                binding.editTextDescription.getText().clear();
                binding.editTextPhoto.getText().clear();
            }
        });
        //write to db
    }

}