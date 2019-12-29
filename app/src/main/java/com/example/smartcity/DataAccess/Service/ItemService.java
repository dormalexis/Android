package com.example.smartcity.DataAccess.Service;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import com.example.smartcity.Model.Item;
import com.example.smartcity.Model.ItemCategory;
import com.example.smartcity.Model.ItemResponseAPI;
import com.example.smartcity.Utilitaries.Preferences;

import org.json.JSONObject;

import java.util.List;

public interface ItemService {

    @GET("Item")
    Call<List<Item>> getItems();

    @GET("Item/itemsByCategory")
    Call<List<Item>> getItemsByCategory(@Body ItemCategory itemCategory);

    @GET("Item/myItems")
    Call<List<Item>> getMyItems();

    @FormUrlEncoded
    @POST("Item")
    Call<ItemResponseAPI> postItem(@Body Item item);

    @PUT("Item")
    Call<Integer> updateItem(@Body Item item);


}
