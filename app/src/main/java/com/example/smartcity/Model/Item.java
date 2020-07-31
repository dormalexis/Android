package com.example.smartcity.Model;

import com.example.smartcity.Exception.DescriptionException;
import com.example.smartcity.Exception.ItemNameException;
import com.example.smartcity.Exception.NotAReal;
import com.example.smartcity.Utilitaries.CheckForms;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Item {

    @SerializedName("itemId")
    private Integer itemId;
    @SerializedName("isVisible")
    private Boolean isVisible;
    @SerializedName("title")
    private String title;
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
    @SerializedName("ownerName")
    private String ownerName;
    @SerializedName("ownerFirstName")
    private String ownerFirstName;
    @SerializedName("isFavorite")
    private Boolean isFavorite;
    @SerializedName("review")
    private List<Review> reviews;
    @SerializedName("rental")
    private List<Rental> rentals;


    public Item() {
    }

    public Item(Integer itemId, Boolean isVisible, String title, String description, String pricePerDay, Integer owner, Integer itemCategory, ArrayList<Picture> pictures, ArrayList<byte[]> picture) throws ItemNameException, DescriptionException, NotAReal {
        setItemId(itemId);
        setVisible(isVisible);
        setTitle(title);
        setDescription(description);
        setPricePerDay(pricePerDay);
        setOwner(owner);
        setItemCategory(itemCategory);
        setPictures(pictures);
    }


    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public List<Rental> getRentals() {
        return rentals;
    }


    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws ItemNameException {
        if (!CheckForms.isValidItemName(title)) throw new ItemNameException();
        this.title = title;
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


    public void setDescription(String description) throws DescriptionException {
        if (!CheckForms.isValidDescription(description)) throw new DescriptionException();
        this.description = description;
    }

    public void setPricePerDay(String pricePerDay) throws NotAReal {
        if (!CheckForms.isValidPrice(pricePerDay)) throw new NotAReal();
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
