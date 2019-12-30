package com.example.smartcity.DataAccess.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.smartcity.DataAccess.Repository.ConnectionDataAccess;
import com.example.smartcity.DataAccess.Repository.ConnectionRepository;
import com.example.smartcity.Model.ApiResponse;
import com.example.smartcity.Model.LoginModel;

public class ConnectionViewModel {
    private ConnectionDataAccess connectionDataAccess;

    public ConnectionViewModel(Context context)
    {
        this.connectionDataAccess = new ConnectionRepository(context);
    }

    public LiveData<ApiResponse> getToken(LoginModel loginModel, Context context) { return connectionDataAccess.getToken(loginModel, context);}
}
