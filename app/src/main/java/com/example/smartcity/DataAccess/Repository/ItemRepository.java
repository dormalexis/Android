package com.example.smartcity.DataAccess.Repository;

import android.content.Context;
import android.util.JsonReader;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smartcity.DataAccess.InternetChecking;
import com.example.smartcity.DataAccess.Service.ItemService;
import com.example.smartcity.Model.Item;
import com.example.smartcity.Model.ItemCategory;
import com.example.smartcity.Model.ItemResponseAPI;
import com.example.smartcity.Model.Locality;
import com.example.smartcity.Utilitaries.RetrofitInstance;

import org.json.JSONObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

public class ItemRepository implements ItemDataAccess
{
    private MutableLiveData<List<Item>> itemsLive;
    private MutableLiveData<List<Item>> myItems;
    private MutableLiveData<Item> itemPost;
    private MutableLiveData<List<Item>> itemsCategoryLive;
    private Integer itemId;
    private InternetChecking internetChecking;
    private MutableLiveData<Item> itemPost;
    Context context;

    public ItemRepository(Context context)
    {
        this.itemsLive = new MutableLiveData<>();
        this.myItems = new MutableLiveData<>();
        this.itemPost = new MutableLiveData<>();
        this.internetChecking = new InternetChecking(context);
        this.itemPost = new MutableLiveData<>();
        this.context = context;

    }

    @Override
    public MutableLiveData<List<Item>> getItems() {

        if(!internetChecking.isNetworkAvailable()) {} // Todo : Renvoie erreur pas de connection
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

    public MutableLiveData<Item> postItem(Item item) {
        if(!internetChecking.isNetworkAvailable()) {} // Todo : Renvoie erreur pas de connection
        ItemService service = RetrofitInstance.getRetrofitInstance(context).create(ItemService.class);
        Call<Item> call = service.postItem(item);

        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if (response.isSuccessful()) {
                    itemPost.setValue(response.body());
                } else {
                    Log.d("POST", "onResponse: response vide");
                }
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Log.i("postFailed", "Post failed");
            }
        });
        return itemPost;
    }

    public void deleteItem(int itemId)
    {
        ItemService service = RetrofitInstance.getRetrofitInstance(context).create(ItemService.class);  //TODO : Service dans constructeur
        Call<Void> call = service.deleteItem(itemId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void updateItem(Item item)
    {
        if(!internetChecking.isNetworkAvailable()) {} // Todo : Renvoie erreur pas de connection
        ItemService service = RetrofitInstance.getRetrofitInstance(context).create(ItemService.class);
        Call<Integer> call = service.updateItem(item.getItemId(),item);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
               if(response.isSuccessful()) System.out.println(response.errorBody().toString());
               else System.out.println(response.message());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                //Log.i("postFailed", "Post failed");
            }
        });
    }


    public MutableLiveData<List<Item>> getMyItems()
    {
        if(!internetChecking.isNetworkAvailable()) {} // Todo : Renvoie erreur pas de connection
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

    public MutableLiveData<List<Item>> getItemsByCategory(ItemCategory itemCategory)
    {
        if(!internetChecking.isNetworkAvailable()) {} // Todo : Renvoie erreur pas de connection
        ItemService service = RetrofitInstance.getRetrofitInstance(context).create(ItemService.class);
        Call<List<Item>> call = service.getItemsByCategory(itemCategory);
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if(response.isSuccessful())
                {
                    Log.i("Ok", response.body().toString());
                    itemsCategoryLive.setValue(response.body());
                }
                else Log.i("PasOk", response.errorBody().toString());
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.i("postFailed", "Connection failed");
            }
        });
        return itemsCategoryLive;

    }


}
