package com.example.flowershop.ui.feedbacks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flowershop.R;
import com.example.flowershop.models.FeedbackModel;
import com.example.flowershop.remote_data.FeedbackListener;

import java.util.ArrayList;
import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.FeedbackViewHolder> {

    private List<FeedbackModel> feedbackList = new ArrayList<>();
    private FeedbackListener listener;

    public FeedbackAdapter(FeedbackListener listener) {
        this.listener = listener;
    }

    public void setFeedbacks(List<FeedbackModel> feedbacks) {
        this.feedbackList = feedbacks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FeedbackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feedback, parent, false);
        return new FeedbackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackViewHolder holder, int position) {
        FeedbackModel feedback = feedbackList.get(position);
        holder.userTextView.setText(feedback.getUser());
        holder.feedbackTextView.setText(feedback.getFeedback());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onFeedbackClicked(feedback);
            }
        });
    }

    @Override
    public int getItemCount() {
        return feedbackList.size();
    }

    static class FeedbackViewHolder extends RecyclerView.ViewHolder {
        TextView userTextView;
        TextView feedbackTextView;

        public FeedbackViewHolder(@NonNull View itemView) {
            super(itemView);
            userTextView = itemView.findViewById(R.id.userTextView);
            feedbackTextView = itemView.findViewById(R.id.feedbackTextView);
        }
    }
}
