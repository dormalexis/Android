package com.example.smartcity.DataAccess.Repository;

import androidx.lifecycle.MutableLiveData;
import com.example.smartcity.Model.ApiResponse;
import com.example.smartcity.Model.Favorite;
import com.example.smartcity.Model.Item;
import java.util.List;

public interface FavoriteDataAccess {
    MutableLiveData<ApiResponse<List<Item>>> getFavorites();
    MutableLiveData<ApiResponse<Favorite>> favorite(int itemId);
}
