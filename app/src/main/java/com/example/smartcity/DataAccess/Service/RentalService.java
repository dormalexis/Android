package com.example.smartcity.DataAccess.Service;

import com.example.smartcity.Model.Rental;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RentalService {

    @GET("Rental/myRentals")
    Call<List<Rental>> getRentals();

    @GET("Rental/waitingForValidation")
    Call<List<Rental>> getValidations();
}