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
    @SerializedName("box")
    private String box;
    @SerializedName("isBlocked")
    private Boolean isBlocked;
    @SerializedName("locality")
    private Integer locality;

    public Person(Context context) {

    }

    public String getBox() {
        return box;
    }

    public void setBox(String box) throws BoxException {
        if(!CheckForms.isValidBox(box)) throw new BoxException();
        this.box = box;
    }

    public Integer getLocality() {
        return locality;
    }

    public void setLocality(Integer locality) {
        this.locality = locality;
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

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setPersonId(Integer personId) {
        personId = personId;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }


    public void setEmail(String email) throws EmailException{
        if(!CheckForms.isValidEmail(email)) throw new EmailException();
        this.email = email;
    }

    public void setFirstName(String firstName) throws FirstNameException {
        if(!CheckForms.isValidName(firstName)) throw new FirstNameException();
        this.firstName = firstName;
    }

    public void setLastName(String lastName) throws NameException {
        if(!CheckForms.isValidName(lastName)) throw new NameException();
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) throws PhoneNumberException {
        if(!CheckForms.isValidPhoneNumber(phoneNumber)) throw new PhoneNumberException();
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) throws PasswordException {
        if(!CheckForms.isValidPassword(password)) throw new PasswordException();
        this.password = password;
    }

    public void setStreet(String street) throws StreetNameException{
        if(!CheckForms.isValidStreetName(street)) throw new StreetNameException();
        this.street = street;
    }
}
