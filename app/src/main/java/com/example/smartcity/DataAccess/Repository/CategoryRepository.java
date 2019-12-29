package com.example.smartcity.DataAccess.Repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.smartcity.DataAccess.InternetChecking;
import com.example.smartcity.DataAccess.Service.CategoryService;
import com.example.smartcity.DataAccess.Service.ItemService;
import com.example.smartcity.Model.Item;
import com.example.smartcity.Model.ItemCategory;
import com.example.smartcity.Utilitaries.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepository
{
    private MutableLiveData<List<ItemCategory>> categoryLive;
    private Context context;
    private InternetChecking internetChecking;

    public CategoryRepository(Context context)
    {
        this.categoryLive = new MutableLiveData<>();
        this.context = context;
        this.internetChecking = new InternetChecking(context);
    }

    public MutableLiveData<List<ItemCategory>> getCategories() {

        // Pour optimiser le réseau, comme les catégories ne vont pas souvent varier,
        // on va garder la liste des catégories telle quelle durant toute la durée de l'
        // application
        if(!internetChecking.isNetworkAvailable()) {} // Todo : Renvoie erreur pas de connection
            CategoryService service = RetrofitInstance.getRetrofitInstance(context).create(CategoryService.class);
            Call<List<ItemCategory>> call = service.getCategory();
            call.enqueue(new Callback<List<ItemCategory>>() {
                @Override
                public void onResponse(Call<List<ItemCategory>> call, Response<List<ItemCategory>> response) {
                    Log.i("works", "Affichage fonctionne");
                    categoryLive.setValue(response.body());
                }

                @Override
                public void onFailure(Call<List<ItemCategory>> call, Throwable t) {
                    Log.i("home", "Affichage des catéries raté");
                }
            });
        return categoryLive;
    }


}
