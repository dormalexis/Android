package com.example.smartcity.Model;

import com.google.gson.annotations.SerializedName;

import java.util.GregorianCalendar;

public class Proposal
{
    @SerializedName("proposalId")
    private Integer proposalId;
    @SerializedName("message")
    private String message;
    @SerializedName("dateFrom")
    private GregorianCalendar dateFrom;
    @SerializedName("dateTo")
    private GregorianCalendar dateTo;
    @SerializedName("rentalId")
    private Integer RentalId;

    public int getProposalId() {
        return proposalId;
    }

    public void setProposalId(int proposalId) {
        this.proposalId = proposalId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GregorianCalendar getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(GregorianCalendar dateFrom) {
        this.dateFrom = dateFrom;
    }

    public GregorianCalendar getDateTo() {
        return dateTo;
    }

    public void setDateTo(GregorianCalendar dateTo) {
        this.dateTo = dateTo;
    }

    public int getRentalId() {
        return RentalId;
    }

    public void setRentalId(int rentalId) {
        RentalId = rentalId;
    }
}
