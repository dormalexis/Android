package com.example.smartcity.DataAccess.Repository;

import android.content.Context;

import com.example.smartcity.Model.LoginModel;


public interface ConnectionDataAccess {
    void getToken(LoginModel loginModel, Context context);
}
