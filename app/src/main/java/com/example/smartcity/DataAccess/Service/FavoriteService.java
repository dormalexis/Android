package com.example.smartcity.DataAccess.Service;
import com.example.smartcity.Model.Favorite;
import com.example.smartcity.Model.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FavoriteService {

    @POST("Favorite/{itemId}")
    Call<Void> favorite(@Path("itemId") int itemId);

    @GET("Favorite/myFavorites")
    Call<List<Item>> getFavorites();
}
