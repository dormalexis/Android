package com.example.smartcity.DataAccess.Repository;
import androidx.lifecycle.MutableLiveData;
import com.example.smartcity.DataAccess.Service.FavoriteService;
import com.example.smartcity.Model.ApiResponse;
import com.example.smartcity.Model.Favorite;
import com.example.smartcity.Model.Item;
import com.example.smartcity.Utilitaries.ApiCodeTrad;
import com.example.smartcity.Utilitaries.ApiResponseErrorCode;
import com.example.smartcity.Utilitaries.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.smartcity.Utilitaries.App.getContext;

public class FavoriteRepository implements FavoriteDataAccess {


    private MutableLiveData<ApiResponse<List<Item>>> favoritesLive = new MutableLiveData<>();
    private MutableLiveData<ApiResponse<Favorite>> favoritePost = new MutableLiveData<>();

    public MutableLiveData<ApiResponse<List<Item>>> getFavorites()
    {
        FavoriteService service = RetrofitInstance.getRetrofitInstance(getContext()).create(FavoriteService.class);
        Call<List<Item>> call = service.getFavorites();
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if(response.isSuccessful())
                {
                    favoritesLive.setValue(new ApiResponse<>(response.body()));
                }
                else
                {
                    favoritesLive.setValue(new ApiResponse<>(ApiCodeTrad.codeErrorToApiResponse(response.code())));
                }

            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                favoritesLive.setValue(new ApiResponse<>(ApiResponseErrorCode.SERVEURERROR));
            }
        });
        return favoritesLive;
    }


    public MutableLiveData<ApiResponse<Favorite>> favorite(int itemId)
    {

        FavoriteService service = RetrofitInstance.getRetrofitInstance(getContext()).create(FavoriteService.class);
        Call<Favorite> call = service.favorite(itemId);
        call.enqueue(new Callback<Favorite>() {
            @Override
            public void onResponse(Call<Favorite> call, Response<Favorite> response) {
                if(response.isSuccessful())
                {
                    favoritePost.setValue(new ApiResponse<>(response.body()));
                }
                else
                {
                    favoritePost.setValue(new ApiResponse<>(ApiCodeTrad.codeErrorToApiResponse(response.code())));
                }

            }

            @Override
            public void onFailure(Call<Favorite> call, Throwable t) {
                favoritesLive.setValue(new ApiResponse<>(ApiResponseErrorCode.SERVEURERROR));
            }
        });
        return favoritePost;
    }
}
