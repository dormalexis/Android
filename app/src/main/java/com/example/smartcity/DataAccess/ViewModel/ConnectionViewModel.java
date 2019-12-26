package com.example.smartcity.DataAccess.ViewModel;

import android.content.Context;

import com.example.smartcity.DataAccess.Repository.ConnectionDataAccess;
import com.example.smartcity.DataAccess.Repository.ConnectionRepository;
import com.example.smartcity.Model.LoginModel;

public class ConnectionViewModel {
    private ConnectionDataAccess connectionDataAccess;

    public ConnectionViewModel(Context context)
    {
        this.connectionDataAccess = new ConnectionRepository(context);
    }

    public void getToken(LoginModel loginModel, Context context) { connectionDataAccess.getToken(loginModel, context);}
}
