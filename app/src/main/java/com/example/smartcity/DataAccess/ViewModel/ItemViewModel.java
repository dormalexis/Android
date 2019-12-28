package com.example.smartcity.DataAccess.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartcity.DataAccess.Repository.ItemDataAccess;
import com.example.smartcity.DataAccess.Repository.ItemRepository;
import com.example.smartcity.Model.Item;
import com.example.smartcity.Model.ItemResponseAPI;

import java.util.List;

public class ItemViewModel extends ViewModel {
    private ItemDataAccess itemRepository;

    public ItemViewModel(Context context)
    {
        itemRepository = new ItemRepository(context);
    }

    public LiveData<List<Item>> getItems() {
        return itemRepository.getItems();
    }
    public int postItem(Item item) {
        return itemRepository.postItem(item);
    }
    public LiveData<List<Item>> getMyItems() {
        return itemRepository.getMyItems();
    }
    public void updateItem(Item item) {itemRepository.updateItem(item);}
}
