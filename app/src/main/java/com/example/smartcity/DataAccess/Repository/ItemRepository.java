package com.example.smartcity.DataAccess.Repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.smartcity.DataAccess.Service.ItemService;
import com.example.smartcity.Model.Item;
import com.example.smartcity.Utilitaries.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemRepository implements ItemDataAccess
{
    MutableLiveData<List<Item>> itemsLive = new MutableLiveData<>();

    @Override
    public MutableLiveData<List<Item>> getItems() {

        ItemService service = RetrofitInstance.getRetrofitInstance().create(ItemService.class);
        Call<List<Item>> call = service.getItems();
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                itemsLive.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.i("home", "Affichage items raté");
            }
        });
        return itemsLive;
    }

    public void postItem(Item item)
    {
        ItemService service = RetrofitInstance.getRetrofitInstance().create(ItemService.class);
        Call<Integer> call = service.postItem(item);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                //Log.i("postOk", response.body().toString());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.i("postFailed", "Post failed");
            }
        });
    }

    public void get(Item item)
    {
        ItemService service = RetrofitInstance.getRetrofitInstance().create(ItemService.class);
        Call<Integer> call = service.postItem(item);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                //Log.i("postOk", response.body().toString());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.i("postFailed", "Post failed");
            }
        });
    }


}
