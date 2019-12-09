package com.example.smartcity.Model;

import com.google.gson.annotations.SerializedName;

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
    //private ItemCategory itemCategory;


    public Item() {}
    public Item(Integer itemId, Boolean isVisible, String name, String description, Double pricePerDay)
    {
        this.name = name;
        this.description = description;
        this.pricePerDay = pricePerDay;
    }

    public Integer getItemId() {
        return itemId;
    }

    public Boolean getVisible() {
        return isVisible;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public void setVisible(Boolean visible) {
        isVisible = visible;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}
