package com.example.smartcity.Model;

import com.google.gson.annotations.SerializedName;

public class Person {
    @SerializedName("personId")
    private Integer personId;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("streetName")
    private String street;
    @SerializedName("streetNumber")
    private Integer streetNumber;
    @SerializedName("box")
    private String box;
    @SerializedName("availabilityDescription")
    private String availabilityDescription;
    @SerializedName("isBlocked")
    private Boolean isBlocked;
    @SerializedName("localitylocalityId")
    private Integer locality;
    @SerializedName("roleroleId")
    private Integer role;



    public Person() { }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public String getBox() {
        return box;
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public void setBox(String box) {
        this.box = box;
    }

    public Integer getLocality() {
        return locality;
    }

    public Integer getRole() {
        return role;
    }

    public void setLocality(Integer locality) {
        this.locality = locality;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getAvailabilityDescription() {
        return availabilityDescription;
    }

    public String getPassword() {
        return password;
    }

    public String getStreet() {
        return street;
    }

    public Integer getPersonId() {
        return personId;
    }

    public String getStreetName() {
        return street;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    public String getAvailability() {
        return availabilityDescription;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setPersonId(Integer personId) {
        personId = personId;
    }

    public void setAvailability(String availability) {
        this.availabilityDescription = availability;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAvailabilityDescription(String availabilityDescription) {
        this.availabilityDescription = availabilityDescription;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
