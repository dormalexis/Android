package com.example.smartcity.Model;

import com.google.gson.annotations.SerializedName;

public class Locality {

    @SerializedName("localityId")
    private Integer localityId;
    @SerializedName("zipCode")
    private Integer zipCode;
    @SerializedName("cityName")
    private String cityName;

    public Integer getLocalityId() {
        return localityId;
    }

    public void setLocalityId(Integer localityId) {
        this.localityId = localityId;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString()
    {
        return zipCode + " - " + cityName;
    }
}
