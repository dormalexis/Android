package com.example.smartcity.Model;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class Review {

    @SerializedName("reviewId")
    private Integer reviewId;
    @SerializedName("dateReview")
    private Date dateReview;
    @SerializedName("nbStars")
    private Float nbStars;
    @SerializedName("comments")
    private String comment;
    @SerializedName("renter")
    private Integer renter;
    @SerializedName("owner")
    private Integer owner;
    @SerializedName("renterName")
    private String renterName;

    public Review() {}

    public String getComment() {
        return comment;
    }

    public Float getNbStars() {
        return nbStars;
    }

    public Date getDateReview() {
        return dateReview;
    }

    public Integer getOwner() {
        return owner;
    }

    public Integer getRenter() {
        return renter;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public String getRenterName() {
        return renterName;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setNbStars(Float nbStars) {
        this.nbStars = nbStars;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public void setDateReview(Date dateReview) {
        this.dateReview = dateReview;
    }

    public void setRenter(Integer renter) {
        this.renter = renter;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public void setRenterName(String renterName) {
        this.renterName = renterName;
    }
}
