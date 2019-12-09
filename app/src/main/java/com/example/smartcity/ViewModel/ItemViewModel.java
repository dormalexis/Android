package com.example.smartcity.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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
}
