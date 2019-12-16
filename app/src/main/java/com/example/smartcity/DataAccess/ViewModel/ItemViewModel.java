package com.example.smartcity.DataAccess.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartcity.DataAccess.Repository.ItemDataAccess;
import com.example.smartcity.DataAccess.Repository.ItemRepository;
import com.example.smartcity.Model.Item;

import java.util.List;

public class ItemViewModel extends ViewModel {
    private ItemDataAccess itemRepository;

    public ItemViewModel()
    {
        itemRepository = new ItemRepository();
    }

    public LiveData<List<Item>> getItems() {
        return itemRepository.getItems();
    }
    public void postItem(Item item) { itemRepository.postItem(item);}
}
