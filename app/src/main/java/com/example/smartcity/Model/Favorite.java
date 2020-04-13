package com.example.smartcity.Model;

import com.google.gson.annotations.SerializedName;

public class Favorite {

    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("pricePerDay")
    private Double pricePerDay;
    @SerializedName("ownerIdentity")
    private String ownerIdentity;
    @SerializedName("ownerNbStars")
    private Double ownerNbStars;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public String getOwnerIdentity() {
        return ownerIdentity;
    }

    public Double getOwnerNbStars() {
        return ownerNbStars;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public void setOwnerIdentity(String ownerIdentity) {
        this.ownerIdentity = ownerIdentity;
    }

    public void setOwnerNbStars(Double ownerNbStars) {
        this.ownerNbStars = ownerNbStars;
    }
}
