package com.example.smartcity.Model;

import com.example.smartcity.Exception.EmailException;
import com.example.smartcity.Exception.PasswordException;
import com.example.smartcity.Utilitaries.CheckForms;
import com.google.gson.annotations.SerializedName;

public class LoginModel {

    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) throws PasswordException {
        if(!CheckForms.isValidPassword(password)) throw new PasswordException();
        this.password = password;
    }

    public void setEmail(String email) throws EmailException {
        if(!CheckForms.isValidEmail(email)) throw new EmailException();
        this.email = email;
    }
}
