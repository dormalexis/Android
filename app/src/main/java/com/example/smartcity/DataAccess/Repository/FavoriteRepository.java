package com.example.smartcity.DataAccess.Repository;
import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.smartcity.DataAccess.InternetChecking;
import com.example.smartcity.DataAccess.Service.FavoriteService;
import com.example.smartcity.Model.ApiResponse;
import com.example.smartcity.Model.Favorite;
import com.example.smartcity.Model.Item;
import com.example.smartcity.Utilitaries.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.smartcity.Utilitaries.App.getContext;

public class FavoriteRepository implements FavoriteDataAccess {


    private MutableLiveData<ApiResponse<List<Item>>> favoritesLive = new MutableLiveData<>();
    private MutableLiveData<ApiResponse<Favorite>> favoritePost = new MutableLiveData<>();
    private InternetChecking internetChecking = new InternetChecking();

    public MutableLiveData<ApiResponse<List<Item>>> getFavorites()
    {
        if(!internetChecking.isNetworkAvailable()) {
            favoritesLive.setValue(new ApiResponse(-1));
            return favoritesLive;
        }
        FavoriteService service = RetrofitInstance.getRetrofitInstance(getContext()).create(FavoriteService.class);
        Call<List<Item>> call = service.getFavorites();
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if(response.isSuccessful())
                {
                    favoritesLive.setValue(new ApiResponse(response.body()));
                }
                else
                {
                    favoritesLive.setValue(new ApiResponse(response.code()));
                }

            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                favoritesLive.setValue(new ApiResponse(500));
            }
        });
        return favoritesLive;
    }


    public MutableLiveData<ApiResponse<Favorite>> favorite(int itemId)
    {
        if(!internetChecking.isNetworkAvailable()) {
            favoritePost.setValue(new ApiResponse(-1));
            return favoritePost;
        }
        FavoriteService service = RetrofitInstance.getRetrofitInstance(getContext()).create(FavoriteService.class);
        Call<Void> call = service.favorite(itemId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful())
                {
                    favoritePost.setValue(new ApiResponse());
                }
                else
                {
                    favoritePost.setValue(new ApiResponse(response.code()));
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                favoritesLive.setValue(new ApiResponse(500));
            }
        });
        return favoritePost;
    }
}
