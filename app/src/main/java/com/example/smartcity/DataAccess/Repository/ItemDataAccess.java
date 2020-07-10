package com.example.smartcity.DataAccess.Repository;

import androidx.lifecycle.MutableLiveData;

import com.example.smartcity.Model.ApiResponse;
import com.example.smartcity.Model.Item;
import com.example.smartcity.Model.ItemCategory;
import com.example.smartcity.Model.ItemResponseAPI;
import com.example.smartcity.Model.PagingResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;

public interface ItemDataAccess {
    MutableLiveData<ApiResponse<PagingResult<Item>>> getItems();
    MutableLiveData<ApiResponse<Item>> postItem(Item item);
    MutableLiveData<ApiResponse<List<Item>>> getMyItems();
    MutableLiveData<ApiResponse> updateItem(Item item);
    MutableLiveData<ApiResponse<List<Item>>> getItemsByCategory(int categoryId);
    MutableLiveData<ApiResponse> deleteItem(int itemId);

}
