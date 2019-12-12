package com.example.smartcity.Model;

import com.google.gson.annotations.SerializedName;

public class Person {
    private Integer PersonId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Integer streetNumber;
    private String streetName;
    private Integer box;
    private String availability;
    private Boolean isBlocked;

    public Person() { }

    public Integer getPersonId() {
        return PersonId;
    }

    public String getStreetName() {
        return streetName;
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

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public Integer getBox() {
        return box;
    }

    public String getAvailability() {
        return availability;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setPersonId(Integer personId) {
        PersonId = personId;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
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

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }
}
