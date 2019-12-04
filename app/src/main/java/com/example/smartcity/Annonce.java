package com.example.smartcity;

public class Annonce {
    private String name;
    private String description;
    private Double pricePerDay;

    public Annonce(String name, String description, Double pricePerDay)
    {
        this.name = name;
        this.description = description;
        this.pricePerDay = pricePerDay;
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
}
