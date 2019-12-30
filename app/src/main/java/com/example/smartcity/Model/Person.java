package com.example.smartcity.Model;

import android.content.Context;

import com.example.smartcity.Exception.BoxException;
import com.example.smartcity.Exception.DescriptionException;
import com.example.smartcity.Exception.EmailException;
import com.example.smartcity.Exception.FirstNameException;
import com.example.smartcity.Exception.NameException;
import com.example.smartcity.Exception.PasswordException;
import com.example.smartcity.Exception.PhoneNumberException;
import com.example.smartcity.Exception.StreetNameException;
import com.example.smartcity.Exception.StreetNumberException;
import com.example.smartcity.Utilitaries.CheckForms;
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

    private Context context;

    public Person(Context context) {
        this.context = context;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public String getBox() {
        return box;
    }

    public void setStreetNumber(String streetNumber) throws StreetNumberException {
        if(!CheckForms.isValidStreetNumber(streetNumber)) throw new StreetNumberException(context);
        this.streetNumber = Integer.parseInt(streetNumber);
    }

    public void setBox(String box) throws BoxException {
        if(!CheckForms.isValidBox(box)) throw new BoxException(context);
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


    public void setEmail(String email) throws EmailException{
        if(!CheckForms.isValidEmail(email)) throw new EmailException(context);
        this.email = email;
    }

    public void setFirstName(String firstName) throws FirstNameException {
        if(!CheckForms.isValidName(firstName)) throw new FirstNameException(context);
        this.firstName = firstName;
    }

    public void setLastName(String lastName) throws NameException {
        if(!CheckForms.isValidName(lastName)) throw new NameException(context);
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) throws PhoneNumberException {
        if(!CheckForms.isValidPhoneNumber(phoneNumber)) throw new PhoneNumberException(context);
        this.phoneNumber = phoneNumber;
    }

    public void setAvailabilityDescription(String availabilityDescription) throws DescriptionException {
        if(!CheckForms.isValidDescription(availabilityDescription)) throw  new DescriptionException(context);
        this.availabilityDescription = availabilityDescription;
    }

    public void setPassword(String password) throws PasswordException {
        if(!CheckForms.isValidPassword(password)) throw new PasswordException(context);
        this.password = password;
    }

    public void setStreet(String street) throws StreetNameException{
        if(!CheckForms.isValidStreetName(street)) throw new StreetNameException(context);
        this.street = street;
    }
}
