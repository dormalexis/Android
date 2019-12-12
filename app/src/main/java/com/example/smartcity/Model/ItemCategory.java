package com.example.smartcity.Model;

import com.google.gson.annotations.SerializedName;

public class ItemCategory {
    @SerializedName("categoryId")
    private Integer categoryId;
    @SerializedName("label")
    private String label;

    public ItemCategory() {}
    public ItemCategory(Integer categoryId, String label)
    {
        this.categoryId = categoryId;
        this.label = label;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public String getLabel() {
        return label;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
