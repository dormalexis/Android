package com.example.smartcity.DataAccess.Repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smartcity.Model.ApiResponse;
import com.example.smartcity.Model.Item;
import com.example.smartcity.Model.Rental;

import java.util.List;

public interface RentalDataAccess {
    MutableLiveData<ApiResponse<List<Rental>>> getRentals();
    MutableLiveData<ApiResponse<List<Rental>>> getValidations();
}
