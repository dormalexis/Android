package com.example.smartcity.Model;

import android.content.Context;

import com.example.smartcity.Exception.DescriptionException;
import com.example.smartcity.Exception.ItemNameException;
import com.example.smartcity.Exception.NotAReal;
import com.example.smartcity.Utilitaries.CheckForms;
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
    @SerializedName("nbStars")
    private Float nbStars;
    @SerializedName("nbAvis")
    private int nbAvis;



    public Item(){}

    public Item(Integer itemId, Boolean isVisible, String name, String description, Double pricePerDay, Integer owner, Integer itemCategory, ArrayList<Picture> pictures) {
        this.itemId = itemId;
        this.isVisible = isVisible;
        this.name = name;
        this.description = description;
        this.pricePerDay = pricePerDay;
        this.owner = owner;
        this.itemCategory = itemCategory;
        this.pictures = pictures;
    }

    public int getNbAvis() {
        return nbAvis;
    }

    public void setNbAvis(int nbAvis) {
        this.nbAvis = nbAvis;
    }

    public Float getNbStars() {
        return nbStars;
    }

    public void setNbStars(Float nbStars) {
        this.nbStars = nbStars;
    }

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

    public void setName(String name) throws ItemNameException{
        if(!CheckForms.isValidItemName(name)) throw new ItemNameException();
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


    public void setDescription(String description) throws DescriptionException{
        if(!CheckForms.isValidDescription(description)) throw new DescriptionException();
        this.description = description;
    }

    public void setPricePerDay(String pricePerDay) throws NotAReal {
        if(!CheckForms.isValidReal(pricePerDay)) throw new NotAReal();
        this.pricePerDay = Double.valueOf(pricePerDay);
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
