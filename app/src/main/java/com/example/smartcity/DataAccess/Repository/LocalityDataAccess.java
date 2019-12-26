package com.example.smartcity.DataAccess.Repository;

import androidx.lifecycle.MutableLiveData;

import com.example.smartcity.Model.Locality;

import java.util.List;

public interface LocalityDataAccess {
    MutableLiveData<List<Locality>> getLocalities();
}
