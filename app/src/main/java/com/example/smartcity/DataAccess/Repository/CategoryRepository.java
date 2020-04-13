package com.example.smartcity.DataAccess.Repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.smartcity.DataAccess.InternetChecking;
import com.example.smartcity.DataAccess.Service.CategoryService;
import com.example.smartcity.DataAccess.Service.ItemService;
import com.example.smartcity.Model.ApiResponse;
import com.example.smartcity.Model.Item;
import com.example.smartcity.Model.ItemCategory;
import com.example.smartcity.Utilitaries.ApiCodeTrad;
import com.example.smartcity.Utilitaries.ApiResponseErrorCode;
import com.example.smartcity.Utilitaries.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepository implements CategoryDataAccess
{
    private MutableLiveData<ApiResponse<List<ItemCategory>>> categoryLive;
    private Context context;
    private InternetChecking internetChecking;

    public CategoryRepository(Context context)
    {
        this.categoryLive = new MutableLiveData<>();
        this.context = context;
        this.internetChecking = new InternetChecking(context);
    }

    public MutableLiveData<ApiResponse<List<ItemCategory>>> getCategories(String locale) {

        // Pour optimiser le réseau, comme les catégories ne vont pas souvent varier,
        // on va garder la liste des catégories telle quelle durant toute la durée de l'
        // application
        locale = locale.substring(0,2);

        if(!internetChecking.isNetworkAvailable()) {
            categoryLive.setValue(new ApiResponse<>(ApiResponseErrorCode.NETWORKFAIL));
            return categoryLive;
        }
            CategoryService service = RetrofitInstance.getRetrofitInstance(context).create(CategoryService.class);
            Call<List<ItemCategory>> call = service.getCategory(locale);
            call.enqueue(new Callback<List<ItemCategory>>() {
                @Override
                public void onResponse(Call<List<ItemCategory>> call, Response<List<ItemCategory>> response) {
                    if(response.isSuccessful())
                    {
                        categoryLive.setValue(new ApiResponse<>(response.body()));
                    }
                    else
                    {
                        categoryLive.setValue(new ApiResponse<>(ApiCodeTrad.codeErrorToApiResponse(response.code())));
                    }
                }

                @Override
                public void onFailure(Call<List<ItemCategory>> call, Throwable t) {
                    categoryLive.setValue(new ApiResponse<>(ApiResponseErrorCode.SERVEURERROR));
                }
            });
        return categoryLive;
    }


}
