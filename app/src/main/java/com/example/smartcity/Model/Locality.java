package com.example.smartcity.Model;

import com.google.gson.annotations.SerializedName;

public class Locality {

    @SerializedName("localityId")
    private Integer localityId;
    @SerializedName("zipCode")
    private Integer zipCode;
    @SerializedName("name")
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return zipCode + " - " + name;
    }
}
