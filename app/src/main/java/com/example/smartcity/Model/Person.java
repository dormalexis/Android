package com.example.smartcity.Model;

import com.google.gson.annotations.SerializedName;

public class Person {
    private Integer personId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String street;
    private Integer box;
    private String availabilityDescription;
    private Boolean isBlocked;

    public Person() { }

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

    public Integer getBox() {
        return box;
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

    public void setBox(Integer box) {
        this.box = box;
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
