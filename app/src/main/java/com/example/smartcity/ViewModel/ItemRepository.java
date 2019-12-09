package com.example.smartcity.ViewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.smartcity.ItemService;
import com.example.smartcity.Model.Item;
import com.example.smartcity.RetrofitItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemRepository implements ItemDataAccess
{
    MutableLiveData<List<Item>> itemsLive = new MutableLiveData<>();

    @Override
    public MutableLiveData<List<Item>> getItems() {

        ItemService service = RetrofitItem.getRetrofitInstance().create(ItemService.class);
        Call<List<Item>> call = service.getItems();
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                itemsLive.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.i("coucou", "Test raté");
            }
        });
        return itemsLive;
    }
}
