package com.example.smartcity.DataAccess.Service;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import com.example.smartcity.Model.Item;

import java.util.List;

public interface ItemService {

    @GET("Item")
    Call<List<Item>> getItems();

    @GET("Item/byCategory/{id}")
    Call<List<Item>> getItemsByCategory(@Path("id") int id);

    @GET("Item/myItems")
    Call<List<Item>> getMyItems();

    @POST("Item")
    Call<Item> postItem(@Body Item item);

    @PUT("Item/{id}")
    Call<Void> updateItem(@Path("id") int id,@Body Item item);

    @DELETE("Item/{id}")
    Call<Void> deleteItem(@Path("id") int id);


}
