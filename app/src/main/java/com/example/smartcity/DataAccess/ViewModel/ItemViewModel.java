package com.example.smartcity.DataAccess.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartcity.DataAccess.Repository.ItemDataAccess;
import com.example.smartcity.DataAccess.Repository.ItemRepository;
import com.example.smartcity.Model.ApiResponse;
import com.example.smartcity.Model.Item;
import com.example.smartcity.Model.ItemCategory;
import com.example.smartcity.Model.ItemResponseAPI;
import com.example.smartcity.Model.PagingResult;

import java.util.List;

public class ItemViewModel extends ViewModel {
    private ItemDataAccess itemRepository;

    public ItemViewModel(Context context)
    {
        itemRepository = new ItemRepository(context);
    }

    public LiveData<ApiResponse<PagingResult<Item>>> getItems() {
        return itemRepository.getItems();
    }
    public LiveData<ApiResponse<Item>> postItem(Item item) {
        return itemRepository.postItem(item);
    }
    public LiveData<ApiResponse<List<Item>>> getMyItems() {
        return itemRepository.getMyItems();
    }
    public LiveData<ApiResponse> updateItem(Item item) { return itemRepository.updateItem(item);}
    public LiveData<ApiResponse> deleteItem(int itemId) { return itemRepository.deleteItem(itemId);}
    public LiveData<ApiResponse<List<Item>>> getItemsByCategory(int categoryId) {
        return itemRepository.getItemsByCategory(categoryId);
    }
}
