package com.example.smartcity.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Item {

    @SerializedName("itemId")
    private Integer itemId;
    @SerializedName("isVisible")
    private Boolean isVisible;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("pricePerDay")
    private Double pricePerDay;
    @SerializedName("owner")
    private Integer owner;
    @SerializedName("itemCategory")
    private Integer itemCategory;
    @SerializedName("picture")
    private ArrayList<Picture> pictures;

    public Boolean getVisible() {
        return isVisible;
    }

    public void setVisible(Boolean visible) {
        isVisible = visible;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Picture> getPictures() {
        return pictures;
    }

    public Integer getOwner() {
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

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public void setItemCategory(Integer itemCategory) {
        this.itemCategory = itemCategory;
    }

    public Integer getItemCategory() {
        return itemCategory;
    }

    public void setPictures(ArrayList<Picture> pictures) {
        this.pictures = pictures;
    }
}
