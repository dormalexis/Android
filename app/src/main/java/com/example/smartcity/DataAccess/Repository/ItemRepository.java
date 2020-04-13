package com.example.smartcity.DataAccess.Repository;

import android.content.Context;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.example.smartcity.DataAccess.InternetChecking;
import com.example.smartcity.DataAccess.Service.ItemService;
import com.example.smartcity.Model.ApiResponse;
import com.example.smartcity.Model.Item;
import com.example.smartcity.Utilitaries.ApiCodeTrad;
import com.example.smartcity.Utilitaries.ApiResponseErrorCode;
import com.example.smartcity.Utilitaries.RetrofitInstance;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemRepository implements ItemDataAccess
{
    private MutableLiveData<ApiResponse<List<Item>>> itemsLive;
    private MutableLiveData<ApiResponse<List<Item>>> myItems;
    private MutableLiveData<ApiResponse<Item>> itemPost;
    private MutableLiveData<ApiResponse<List<Item>>> itemsCategoryLive;
    private Integer itemId;
    private InternetChecking internetChecking;
    private MutableLiveData<ApiResponse> deleteLive;
    private MutableLiveData<ApiResponse> updateLive;

    Context context;

    public ItemRepository(Context context)
    {
        this.itemsLive = new MutableLiveData<>();
        this.myItems = new MutableLiveData<>();
        this.itemPost = new MutableLiveData<>();
        this.internetChecking = new InternetChecking(context);
        this.itemPost = new MutableLiveData<>();
        this.context = context;
        this.itemsCategoryLive = new MutableLiveData<>();
        this.updateLive = new MutableLiveData<>();
        this.deleteLive = new MutableLiveData<>();
    }

    @Override
    public MutableLiveData<ApiResponse<List<Item>>> getItems() {

        ItemService service = RetrofitInstance.getRetrofitInstance(context).create(ItemService.class);
        Call<List<Item>> call = service.getItems();
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if(response.isSuccessful())
                {
                    itemsLive.setValue(new ApiResponse<>(response.body()));
                }
                else
                {
                    itemsLive.setValue(new ApiResponse<>(ApiCodeTrad.codeErrorToApiResponse(response.code())));
                }

            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                itemsLive.setValue(new ApiResponse<>(ApiResponseErrorCode.SERVEURERROR));
            }
        });
        return itemsLive;
    }

    public MutableLiveData<ApiResponse<Item>> postItem(Item item) {
        if(!internetChecking.isNetworkAvailable()) {
            itemPost.setValue(new ApiResponse<>(ApiResponseErrorCode.NETWORKFAIL));
            return itemPost;
        }
        ItemService service = RetrofitInstance.getRetrofitInstance(context).create(ItemService.class);
        Call<Item> call = service.postItem(item);

        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response){
                if (response.isSuccessful()) {
                    itemPost.setValue(new ApiResponse<>(response.body()));
                } else {
                    Log.i("test", "" + response.code());
                    itemPost.setValue(new ApiResponse<>(ApiCodeTrad.codeErrorToApiResponse(response.code())));
                }
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                itemPost.setValue(new ApiResponse<>(ApiResponseErrorCode.SERVEURERROR));
            }
        });
        return itemPost;
    }

    public MutableLiveData<ApiResponse> deleteItem(int itemId)
    {
        if(!internetChecking.isNetworkAvailable()) {
            deleteLive.setValue(new ApiResponse(ApiResponseErrorCode.NETWORKFAIL));
            return deleteLive;
        }

        ItemService service = RetrofitInstance.getRetrofitInstance(context).create(ItemService.class);  //TODO : Service dans constructeur
        Call<Void> call = service.deleteItem(itemId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful())
                {
                    deleteLive.setValue(new ApiResponse());
                }
                else
                {
                    deleteLive.setValue(new ApiResponse(ApiCodeTrad.codeErrorToApiResponse(response.code())));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                deleteLive.setValue(new ApiResponse(ApiResponseErrorCode.SERVEURERROR));
            }
        });
        return deleteLive;
    }

    public MutableLiveData<ApiResponse> updateItem(Item item)
    {
        if(!internetChecking.isNetworkAvailable()) {
            updateLive.setValue(new ApiResponse(ApiResponseErrorCode.NETWORKFAIL));
            return updateLive;
        }
        ItemService service = RetrofitInstance.getRetrofitInstance(context).create(ItemService.class);
        Call<Void> call = service.updateItem(item.getItemId(),item);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful())
                {
                    updateLive.setValue(new ApiResponse());
                }
                else
                {
                    updateLive.setValue(new ApiResponse(ApiCodeTrad.codeErrorToApiResponse(response.code())));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                updateLive.setValue(new ApiResponse(ApiResponseErrorCode.SERVEURERROR));
            }
        });
        return updateLive;
    }


    public MutableLiveData<ApiResponse<List<Item>>> getMyItems()
    {
        if(!internetChecking.isNetworkAvailable()) {
            myItems.setValue(new ApiResponse<>(ApiResponseErrorCode.NETWORKFAIL));
            return myItems;
        }
        ItemService service = RetrofitInstance.getRetrofitInstance(context).create(ItemService.class);
        Call<List<Item>> call = service.getMyItems();
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if(response.isSuccessful())
                {
                    myItems.setValue(new ApiResponse<>(response.body()));
                }
                else
                {
                    myItems.setValue(new ApiResponse<>(ApiCodeTrad.codeErrorToApiResponse(response.code())));
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                myItems.setValue(new ApiResponse<>(ApiResponseErrorCode.SERVEURERROR));
            }
        });
        return myItems;
    }

    public MutableLiveData<ApiResponse<List<Item>>> getItemsByCategory(int categoryId)
    {
        if(!internetChecking.isNetworkAvailable()) {
            itemsCategoryLive.setValue(new ApiResponse<>(ApiResponseErrorCode.NETWORKFAIL));
            return itemsCategoryLive;
        }
        ItemService service = RetrofitInstance.getRetrofitInstance(context).create(ItemService.class);
        Call<List<Item>> call = service.getItemsByCategory(categoryId);
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if(response.isSuccessful())
                {
                    itemsCategoryLive.setValue(new ApiResponse<>(response.body()));
                }
                else
                {
                    itemsCategoryLive.setValue(new ApiResponse<>(ApiCodeTrad.codeErrorToApiResponse(response.code())));
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                itemsCategoryLive.setValue(new ApiResponse<>(ApiResponseErrorCode.SERVEURERROR));
            }
        });
        return itemsCategoryLive;

    }


}
