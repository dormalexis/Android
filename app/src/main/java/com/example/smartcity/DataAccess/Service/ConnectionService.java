package com.example.smartcity.DataAccess.Service;

import com.example.smartcity.Model.LoginModel;
import com.example.smartcity.Model.Person;
import com.example.smartcity.Model.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ConnectionService {

    @POST("Jwt")
    Call<TokenResponse> getToken(@Body LoginModel loginModel);
}
