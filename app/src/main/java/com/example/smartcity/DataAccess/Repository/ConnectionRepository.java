package com.example.smartcity.DataAccess.Repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.smartcity.DataAccess.InternetChecking;
import com.example.smartcity.DataAccess.Service.ConnectionService;
import com.example.smartcity.DataAccess.Service.ItemService;
import com.example.smartcity.Model.ApiResponse;
import com.example.smartcity.Model.LoginModel;
import com.example.smartcity.Model.TokenResponse;
import com.example.smartcity.Utilitaries.ApiCodeTrad;
import com.example.smartcity.Utilitaries.ApiResponseErrorCode;
import com.example.smartcity.Utilitaries.Preferences;
import com.example.smartcity.Utilitaries.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConnectionRepository implements ConnectionDataAccess {

    private Context context;
    private InternetChecking internetChecking;
    private MutableLiveData<ApiResponse> tokenLive;


    public ConnectionRepository(Context context)
    {
        this.context = context;
        this.tokenLive = new MutableLiveData<>();
        this.internetChecking = new InternetChecking(context);

    }
    public MutableLiveData<ApiResponse> getToken(LoginModel loginModel, Context context) {
        if(!internetChecking.isNetworkAvailable()) {
            tokenLive.setValue(new ApiResponse(ApiResponseErrorCode.NETWORKFAIL));
        }
        ConnectionService service = RetrofitInstance.getRetrofitInstance(context).create(ConnectionService.class);
        Call<TokenResponse> call = service.getToken(loginModel);
        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if(response.isSuccessful())
                {
                    tokenLive.setValue(new ApiResponse<>(response.body()));
                    Preferences.saveToken(response.body().getAccessToken(),context);
                }
                else
                {
                    tokenLive.setValue(new  ApiResponse<>(ApiCodeTrad.codeErrorToApiResponse(response.code())));
                }

            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                tokenLive.setValue(new ApiResponse<>(ApiResponseErrorCode.SERVEURERROR));
            }
        });
        return tokenLive;
    }
}
