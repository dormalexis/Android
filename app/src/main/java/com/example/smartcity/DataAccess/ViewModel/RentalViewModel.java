package com.example.smartcity.DataAccess.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartcity.DataAccess.Repository.RentalDataAccess;
import com.example.smartcity.DataAccess.Repository.RentalRepository;
import com.example.smartcity.Model.ApiResponse;
import com.example.smartcity.Model.Rental;

import java.util.Date;
import java.util.List;

public class RentalViewModel extends ViewModel {

    private RentalDataAccess rentalDataAccess;

    public RentalViewModel(Context context)
    {
        this.rentalDataAccess = new RentalRepository(context);
    }

    public LiveData<ApiResponse<List<Rental>>> getRentals() {return rentalDataAccess.getRentals();}
    public LiveData<ApiResponse<List<Rental>>> getValidations() {return rentalDataAccess.getValidations();}
}
