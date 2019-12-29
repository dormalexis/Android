package com.example.smartcity.DataAccess.Repository;

import androidx.lifecycle.MutableLiveData;

import com.example.smartcity.Model.Item;
import com.example.smartcity.Model.ItemCategory;
import com.example.smartcity.Model.ItemResponseAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;

public interface ItemDataAccess {
    MutableLiveData<List<Item>> getItems();
    int postItem(Item item);
    MutableLiveData<List<Item>> getMyItems();
    void updateItem(Item item);
    MutableLiveData<List<Item>> getItemsByCategory(ItemCategory itemCategory);

}
