package com.example.smartcity.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;

public class ItemResponseAPI {
    @Expose
    @SerializedName("itemId")
    private Integer itemId;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("description")
    private String description;
    @Expose
    @SerializedName("isVisible")
    private boolean isVisible;
    @Expose
    @SerializedName("pricePerDay")
    private Double pricePerDay;
    @Expose
    @SerializedName("itemCategory")
    private Integer itemCategory;
    @Expose
    @SerializedName("owner")
    private Integer owner;
    @Expose
    @SerializedName("itemCategoryNavigation")
    private String itemCategoryNavigation;
    @Expose
    @SerializedName("ownerNavigation")
    private String ownerNavigation;
    @Expose
    @SerializedName("itemDownTime")
    private Array itemDownTime;
    @Expose
    @SerializedName("picture")
    private Array picture;
    @Expose
    @SerializedName("rental")
    private Array rental;

    public ItemResponseAPI(Integer itemId, String name, String description, boolean isVisible, Double pricePerDay, Integer itemCategory, Integer owner, String itemCategoryNavigation, String ownerNavigation, Array itemDownTime, Array picture, Array rental) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.isVisible = isVisible;
        this.pricePerDay = pricePerDay;
        this.itemCategory = itemCategory;
        this.owner = owner;
        this.itemCategoryNavigation = itemCategoryNavigation;
        this.ownerNavigation = ownerNavigation;
        this.itemDownTime = itemDownTime;
        this.picture = picture;
        this.rental = rental;
    }

    public String getItemCategoryNavigation() {
        return itemCategoryNavigation;
    }

    public void setItemCategoryNavigation(String itemCategoryNavigation) {
        this.itemCategoryNavigation = itemCategoryNavigation;
    }

    public String getOwnerNavigation() {
        return ownerNavigation;
    }

    public void setOwnerNavigation(String ownerNavigation) {
        this.ownerNavigation = ownerNavigation;
    }

    public Array getItemDownTime() {
        return itemDownTime;
    }

    public void setItemDownTime(Array itemDownTime) {
        this.itemDownTime = itemDownTime;
    }

    public Array getPicture() {
        return picture;
    }

    public void setPicture(Array picture) {
        this.picture = picture;
    }

    public Array getRental() {
        return rental;
    }

    public void setRental(Array rental) {
        this.rental = rental;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public Integer getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(Integer itemCategory) {
        this.itemCategory = itemCategory;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }


}
