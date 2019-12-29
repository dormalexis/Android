package com.example.smartcity.DataAccess.Repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.smartcity.DataAccess.InternetChecking;
import com.example.smartcity.DataAccess.Service.CategoryService;
import com.example.smartcity.DataAccess.Service.LocalityService;
import com.example.smartcity.Model.ItemCategory;
import com.example.smartcity.Model.Locality;
import com.example.smartcity.Utilitaries.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocalityRepository implements LocalityDataAccess {
    private MutableLiveData<List<Locality>> localityLive;
    private Context context;
    private InternetChecking internetChecking;

    public LocalityRepository(Context context)
    {
        localityLive = new MutableLiveData<>();
        this.internetChecking = new InternetChecking(context);
        this.context = context;
    }


    public MutableLiveData<List<Locality>> getLocalities() {
        if(!internetChecking.isNetworkAvailable()) {} // Todo : Renvoie erreur pas de connection
        LocalityService service = RetrofitInstance.getRetrofitInstance(context).create(LocalityService.class);
        Call<List<Locality>> call = service.getLocalities();
        call.enqueue(new Callback<List<Locality>>() {
            @Override
            public void onResponse(Call<List<Locality>> call, Response<List<Locality>> response) {
                Log.i("works", "Affichage fonctionne");
                localityLive.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Locality>> call, Throwable t) {
                Log.i("home", "Affichage des catéries raté");
            }
        });
        return localityLive;
    }
}

