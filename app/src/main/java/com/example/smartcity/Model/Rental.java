package com.example.smartcity.Model;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.util.Date;

public class Rental {

    @SerializedName("rentalId")
    private Integer rentalId;
    @SerializedName("dateFrom")
    private Date dateFrom;
    @SerializedName("dateTo")
    private Date dateTo;
    @SerializedName("isPaid")
    private Boolean isPaid;
    @SerializedName("isValid")
    private Boolean isValid;
    @SerializedName("paidPrice")
    private Double PaidPrice;
    @SerializedName("starsNumber")
    private Integer starsNumber;
    @SerializedName("renter")
    private Integer Renter;
    @SerializedName("item")
    private Integer item;


    public Rental(Date dateFrom, Date dateTo, Integer item, Double paidPrice)
    {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.item = item;
        this.PaidPrice = paidPrice;
        this.isValid = false;
        this.isPaid = false;
    }
    public Boolean getPaid() {
        return isPaid;
    }

    public Boolean getValid() {
        return isValid;
    }

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
