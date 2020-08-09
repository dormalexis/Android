package com.example.smartcity.Model;

import com.auth0.android.jwt.JWT;

import java.util.Date;

public class TokenResponse {
    private String accessToken;
    private Date expirationDate;

    public Date getExpirationDate() {
        return expirationDate;
    }

    public String getAccessToken() {
        return accessToken;

    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
