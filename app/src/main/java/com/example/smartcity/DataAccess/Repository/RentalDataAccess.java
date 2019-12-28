package com.example.smartcity.DataAccess.Repository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.smartcity.Model.Item;
import com.example.smartcity.Model.Rental;

import java.util.List;

public interface RentalDataAccess {
    MutableLiveData<List<Rental>> getRentals();
}
