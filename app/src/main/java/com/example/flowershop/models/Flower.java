package com.example.flowershop.models;

public class Flower {
    private String title;
    private String description;
    private String photo;
    private String price;



    public Flower() {

    }

    public Flower(String title, String description, String photo, String price) {
        this.title = title;
        this.description = description;
        this.photo = photo;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
