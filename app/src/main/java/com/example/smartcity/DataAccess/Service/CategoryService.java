package com.example.smartcity.DataAccess.Service;

import com.example.smartcity.Model.ItemCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryService {

    @GET("ItemCategory")
    Call<List<ItemCategory>> getCategory();
}
