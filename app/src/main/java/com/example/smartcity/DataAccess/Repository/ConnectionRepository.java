package com.example.smartcity.DataAccess.Repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.smartcity.DataAccess.Service.ConnectionService;
import com.example.smartcity.DataAccess.Service.ItemService;
import com.example.smartcity.Model.LoginModel;
import com.example.smartcity.Model.TokenResponse;
import com.example.smartcity.Utilitaries.Preferences;
import com.example.smartcity.Utilitaries.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConnectionRepository implements ConnectionDataAccess {


    public void getToken(LoginModel loginModel, Context context) {

        ConnectionService service = RetrofitInstance.getRetrofitInstance().create(ConnectionService.class);
        Call<TokenResponse> call = service.getToken(loginModel);
        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful()) {
                    Log.i("postSuccess", Preferences.getToken(context));
                    Preferences.saveToken(response.body().getAccess_token(), context);
                } else Log.i("postFailed", response.errorBody().toString());

            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Log.i("connectionFailed", "Connection failed");
            }
        });
    }
}
