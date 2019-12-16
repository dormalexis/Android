package com.example.smartcity.DataAccess.Repository;

import androidx.lifecycle.MutableLiveData;

import com.example.smartcity.Model.Item;

import java.util.List;

public interface ItemDataAccess {
    MutableLiveData<List<Item>> getItems();
    void postItem(Item item);
}
