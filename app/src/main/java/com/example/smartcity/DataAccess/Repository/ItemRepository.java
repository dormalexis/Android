package com.example.smartcity.DataAccess.Repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.smartcity.DataAccess.Service.ItemService;
import com.example.smartcity.Model.Item;
import com.example.smartcity.Model.Locality;
import com.example.smartcity.Utilitaries.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemRepository implements ItemDataAccess
{
    private MutableLiveData<List<Item>> itemsLive;
    private MutableLiveData<List<Item>> myItems;
    private MutableLiveData<Item> itemPost;
    private Integer itemId;
    Context context;

    public ItemRepository(Context context)
    {
        this.itemsLive = new MutableLiveData<>();
        this.myItems = new MutableLiveData<>();
        this.itemPost = new MutableLiveData<>();
        this.context = context;
    }

    @Override
    public MutableLiveData<List<Item>> getItems() {

        ItemService service = RetrofitInstance.getRetrofitInstance(context).create(ItemService.class);
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

    public Integer postItem(Item item)
    {
        ItemService service = RetrofitInstance.getRetrofitInstance(context).create(ItemService.class);
        Call<Item> call = service.postItem(item);

        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                itemId = response.body().getItemId();
            }
            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Log.i("postFailed", "Post failed");
            }
        });
        return itemId;
    }

    public MutableLiveData<List<Item>> getMyItems()
    {
        ItemService service = RetrofitInstance.getRetrofitInstance(context).create(ItemService.class);
        Call<List<Item>> call = service.getMyItems();
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if(response.isSuccessful())
                {
                    Log.i("Ok", response.body().toString());
                    myItems.setValue(response.body());
                }
                else Log.i("PasOk", response.errorBody().toString());
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.i("postFailed", "Connection failed");
            }
        });
        return myItems;
    }


}
