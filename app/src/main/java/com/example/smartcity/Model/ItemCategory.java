package com.example.smartcity.Model;

public class ItemCategory {

    private Integer category;
    private String label;

    public ItemCategory() {}
    public ItemCategory(Integer categoryId, String label)
    {
        this.category = categoryId;
    }

    public String getLabel() {
        return label;
    }

    public Integer getCategory() {
        return category;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    @Override
    public String toString()
    {
        return label;
    }
}
