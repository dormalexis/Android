package com.example.smartcity.DataAccess.Service;

import com.example.smartcity.Model.Rental;
import com.example.smartcity.Model.RentalDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface RentalService {

    @GET("Rental/renter/historic")
    Call<List<RentalDTO>> getRentalsRenterHistoric();

    @GET("Rental/renter/inProgress")
    Call<List<RentalDTO>> getRentalsRenterInProgress();

    @GET("Rental/owner/historic")
    Call<List<RentalDTO>> getRentalsOwnerHistoric();

    @GET("Rental/owner/inProgress")
    Call<List<RentalDTO>> getRentalsOwnerInProgress();

    @GET("Rental")
    Call<List<RentalDTO>> getRentals();

    @POST("Rental")
    Call<RentalDTO> postRental(@Body Rental rental);

    @PUT("Rental/{id}")
    Call<Void> validRental(@Path("id") int id, @Body boolean isValid);

}
