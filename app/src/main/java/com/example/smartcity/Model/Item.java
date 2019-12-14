package com.example.smartcity.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Item {
    /*
    @SerializedName("itemId")
    private Integer itemId;
    @SerializedName("isVisible")
    private Boolean isVisible;
    @SerializedName("name")
    private String name;
     */
    @SerializedName("description")
    private String description;
    @SerializedName("pricePerDay")
    private Double pricePerDay;
    @SerializedName("owner")
    private Person owner;
    @SerializedName("picture")
    private ArrayList<Picture> pictures;


    public ArrayList<Picture> getPictures() {
        return pictures;
    }

    public Person getOwner() {
        return owner;
    }

    public String getDescription() {
        return description;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public void setPictures(ArrayList<Picture> pictures) {
        this.pictures = pictures;
    }
}
