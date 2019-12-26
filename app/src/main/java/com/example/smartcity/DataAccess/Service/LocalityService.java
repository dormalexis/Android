package com.example.smartcity.DataAccess.Service;

import com.example.smartcity.Model.Locality;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LocalityService {
    @GET("Locality")
    Call<List<Locality>> getLocalities();
}
