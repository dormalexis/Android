package com.example.smartcity.DataAccess.Repository;

import androidx.lifecycle.MutableLiveData;
import com.example.smartcity.Model.ApiResponse;
import com.example.smartcity.Model.Rental;
import com.example.smartcity.Model.RentalDTO;

import java.util.List;

public interface RentalDataAccess {

    MutableLiveData<ApiResponse<List<RentalDTO>>> getRentalsRenterHistoric();

    MutableLiveData<ApiResponse<List<RentalDTO>>> getRentalsRenterInProgress();

    MutableLiveData<ApiResponse<List<RentalDTO>>> getRentalsOwnerHistoric();

    MutableLiveData<ApiResponse<List<RentalDTO>>> getRentalsOwnerInProgress();

    MutableLiveData<ApiResponse> postRental(Rental rental);

    MutableLiveData<ApiResponse<List<RentalDTO>>> getRentals();

    MutableLiveData<ApiResponse> validRental(int id, boolean isValid);


}
