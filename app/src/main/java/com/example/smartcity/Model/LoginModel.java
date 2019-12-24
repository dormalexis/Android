package com.example.smartcity.Model;

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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
