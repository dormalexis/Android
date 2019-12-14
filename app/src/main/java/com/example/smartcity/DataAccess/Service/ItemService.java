package com.example.smartcity.DataAccess.Service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


import com.example.smartcity.Model.Item;

import java.util.List;

public interface ItemService {

    @GET("Item")
    Call<List<Item>> getItems();

    @POST("Item")
    Call<Integer> postItem(@Body Item item);
}
