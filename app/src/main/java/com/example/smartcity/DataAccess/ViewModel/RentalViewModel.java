package com.example.smartcity.DataAccess.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cloudinary.Api;
import com.example.smartcity.DataAccess.Repository.RentalDataAccess;
import com.example.smartcity.DataAccess.Repository.RentalRepository;
import com.example.smartcity.Model.ApiResponse;
import com.example.smartcity.Model.Rental;
import com.example.smartcity.Model.RentalDTO;

import java.util.Date;
import java.util.List;

public class RentalViewModel extends ViewModel {

    private RentalDataAccess rentalDataAccess;

    public RentalViewModel()
    {
        this.rentalDataAccess = new RentalRepository();
    }

    public LiveData<ApiResponse<List<RentalDTO>>> getRentalsOwnerHistoric() {return rentalDataAccess.getRentalsOwnerHistoric();}
    public LiveData<ApiResponse<List<RentalDTO>>> getRentalsOwnerInProgress() {return rentalDataAccess.getRentalsOwnerInProgress();}
    public LiveData<ApiResponse<List<RentalDTO>>> getRentalsRenterHistoric() {return rentalDataAccess.getRentalsRenterHistoric();}
    public LiveData<ApiResponse<List<RentalDTO>>> getRentalsRenterInProgress() {return rentalDataAccess.getRentalsRenterInProgress();}
    public LiveData<ApiResponse> postRental(Rental rental){return rentalDataAccess.postRental(rental);}
    public LiveData<ApiResponse> validRental(int id, boolean isValid) { return  rentalDataAccess.validRental(id, isValid);}
}
