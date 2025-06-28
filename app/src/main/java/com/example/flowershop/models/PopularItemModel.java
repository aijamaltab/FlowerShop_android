package com.example.flowershop.models;

public class PopularItemModel {
    private int imageResource;
    private String itemName;

    public PopularItemModel(int imageResource, String itemName) {
        this.imageResource = imageResource;
        this.itemName = itemName;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getItemName() {
        return itemName;
    }
}

