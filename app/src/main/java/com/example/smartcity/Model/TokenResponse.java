package com.example.smartcity.Model;

public class TokenResponse {
    private String access_token;
    private Integer expires_in;

    public Integer getExpires_in() {
        return expires_in;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }
}