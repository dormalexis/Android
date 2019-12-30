package com.example.smartcity.DataAccess.Service;

import com.example.smartcity.Model.Rental;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RentalService {

    @GET("Rental/myRentals")
    Call<List<Rental>> getRentals();

    @GET("Rental/waitingForValidation")
    Call<List<Rental>> getValidations();

    @POST("Rental")
    Call<Void> postRental(@Body Rental rental);
}
