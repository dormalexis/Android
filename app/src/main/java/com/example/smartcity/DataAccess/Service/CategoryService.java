package com.example.smartcity.DataAccess.Service;

import com.example.smartcity.Model.ItemCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CategoryService {

    @GET("ItemCategory/translate/{locale}")
    Call<List<ItemCategory>> getCategory(@Path("locale") String locale);

}
