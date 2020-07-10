package com.example.smartcity.DataAccess.Repository;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import com.example.smartcity.DataAccess.InternetChecking;
import com.example.smartcity.DataAccess.Service.LocalityService;
import com.example.smartcity.Model.ApiResponse;
import com.example.smartcity.Model.Locality;
import com.example.smartcity.Utilitaries.RetrofitInstance;
import com.example.smartcity.Utilitaries.StatusCode;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocalityRepository implements LocalityDataAccess {
    private MutableLiveData<ApiResponse<List<Locality>>> localityLive;
    private Context context;
    private InternetChecking internetChecking;

    public LocalityRepository(Context context)
    {
        localityLive = new MutableLiveData<>();
        this.internetChecking = new InternetChecking();
        this.context = context;
    }


    public MutableLiveData<ApiResponse<List<Locality>>> getLocalities() {
        if(!internetChecking.isNetworkAvailable()) {
            localityLive.setValue(new ApiResponse(StatusCode.NETWORKFAIL));
            return localityLive;
        }
        LocalityService service = RetrofitInstance.getRetrofitInstance(context).create(LocalityService.class);
        Call<List<Locality>> call = service.getLocalities();
        call.enqueue(new Callback<List<Locality>>() {
            @Override
            public void onResponse(Call<List<Locality>> call, Response<List<Locality>> response) {
                if(response.isSuccessful())
                {
                    localityLive.setValue(new ApiResponse(response.body()));
                }
                else
                {
                    localityLive.setValue(new ApiResponse(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<Locality>> call, Throwable t) {
                localityLive.setValue(new ApiResponse(StatusCode.INTERNALSERVERERROR));
            }
        });
        return localityLive;
    }
}

