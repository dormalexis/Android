package com.example.smartcity.DataAccess.Service;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import com.example.smartcity.Model.Item;
import com.example.smartcity.Model.ItemResponseAPI;
import com.example.smartcity.Utilitaries.Preferences;

import org.json.JSONObject;

import java.util.List;

public interface ItemService {

    @GET("Item")
    Call<List<Item>> getItems();

    @GET("Item/myItems")
    Call<List<Item>> getMyItems();

    @POST("Item")
    Call<Item> postItem(@Body Item item);

    @PUT("Item/{id}")
    Call<Integer> updateItem(@Path("id") int id,@Body Item item);

    @DELETE("Item/{id}")
    Call<Void> deleteItem(@Path("id") int id);


}
