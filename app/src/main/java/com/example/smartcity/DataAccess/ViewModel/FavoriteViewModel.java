package com.example.smartcity.DataAccess.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartcity.DataAccess.Repository.FavoriteDataAccess;
import com.example.smartcity.DataAccess.Repository.FavoriteRepository;
import com.example.smartcity.Model.ApiResponse;
import com.example.smartcity.Model.Favorite;
import com.example.smartcity.Model.Item;

import java.util.List;


public class FavoriteViewModel extends ViewModel {

    private FavoriteDataAccess favoriteDataAccess = new FavoriteRepository();


    public LiveData<ApiResponse<List<Item>>> getFavorites() {
        return favoriteDataAccess.getFavorites();
    }

    public void favorite(int itemId)
    {
        favoriteDataAccess.favorite(itemId);
    }

}
