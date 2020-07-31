package com.example.smartcity.Model;

import java.util.Date;

public class RentalDTO {

    private Integer rentalId;
    private Date dateFrom;
    private Date dateTo;
private  Boolean isPaid;
    private Boolean isValid;
    private Double paidPrice;
    private String renterLastName;
    private String renterFirstName;
    private String itemTitle;

    public RentalDTO() {}

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public Double getPaidPrice() {
        return paidPrice;
    }

    public Integer getRentalId() {
        return rentalId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public String getRenterFirstName() {
        return renterFirstName;
    }

    public Boolean isPaid() {
        return isPaid;
    }

    public Boolean isValid() {
        return isValid;
    }

    public String getRenterLastName() {
        return renterLastName;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public void setPaidPrice(double paidPrice) {
        this.paidPrice = paidPrice;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public void setRenterFirstName(String renterFirstName) {
        this.renterFirstName = renterFirstName;
    }

    public void setRenterLastName(String renterLastName) {
        this.renterLastName = renterLastName;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
