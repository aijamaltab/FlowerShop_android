package com.example.flowershop.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedbackModel {

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("feedback")
    @Expose
    private String feedback;
    @SerializedName("user")
    @Expose
    private String user;

    public FeedbackModel(String key, String feedback, String user) {
        this.key = key;
        this.feedback = feedback;
        this.user = user;
    }

    public FeedbackModel() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public  String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public  String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
