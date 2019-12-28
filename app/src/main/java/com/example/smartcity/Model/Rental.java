package com.example.smartcity.Model;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class Rental {

    @SerializedName("rentalId")
    private int rentalId;
    @SerializedName("dateFrom")
    private Date dateFrom;
    @SerializedName("dateTo")
    private Date dateTo;
    @SerializedName("isPaid")
    private boolean isPaid;
    @SerializedName("isValid")
    private Boolean isValid;
    @SerializedName("PaidPrice")
    private double PaidPrice;
    @SerializedName("starsNumber")
    private int starsNumber;
    @SerializedName("Renter")
    private int Renter;
    @SerializedName("Item")
    private int item;

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public Boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public double getPaidPrice() {
        return PaidPrice;
    }

    public void setPaidPrice(double paidPrice) {
        PaidPrice = paidPrice;
    }

    public int getStarsNumber() {
        return starsNumber;
    }

    public void setStarsNumber(int starsNumber) {
        this.starsNumber = starsNumber;
    }

    public int getRenter() {
        return Renter;
    }

    public void setRenter(int renter) {
        Renter = renter;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }
}
