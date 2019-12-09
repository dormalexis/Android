package com.example.smartcity.Model;

public class Item {
    private Integer itemId;
    private Boolean isVisible;
    private String name;
    private String description;
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
