package com.example.smartcity.DataAccess.Repository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.smartcity.Model.ApiResponse;
import com.example.smartcity.Model.LoginModel;


public interface ConnectionDataAccess {
    MutableLiveData<ApiResponse> getToken(LoginModel loginModel);
}
