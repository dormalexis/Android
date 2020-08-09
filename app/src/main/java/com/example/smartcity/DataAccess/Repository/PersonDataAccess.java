package com.example.smartcity.DataAccess.Repository;
import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.smartcity.Model.ApiResponse;
import com.example.smartcity.Model.Person;

public interface PersonDataAccess {
    MutableLiveData<ApiResponse> postPerson(Person person);
    MutableLiveData<ApiResponse> updatePerson(Person person);
    MutableLiveData<ApiResponse<Person>> getPerson(Integer personId);
}
