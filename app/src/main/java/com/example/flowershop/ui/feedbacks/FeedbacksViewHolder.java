package com.example.flowershop.ui.feedbacks;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flowershop.Interface.ItemClickListner;
import com.example.flowershop.R;

public class FeedbacksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView feedbackEditText, userEditText;
    public ItemClickListner listner;



    public FeedbacksViewHolder(@NonNull View itemView) {
        super(itemView);

        feedbackEditText = itemView.findViewById(R.id.feedbackEditText);
        userEditText = itemView.findViewById(R.id.userTextView);
    }

    public void setItemClickListner(ItemClickListner listner)
    {
        this.listner = listner;
    }

    @Override
    public void onClick(View view)
    {
        listner.onClick(view, getAdapterPosition(), false);
    }
}