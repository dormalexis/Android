package com.example.smartcity.DataAccess.Repository;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.example.smartcity.DataAccess.InternetChecking;
import com.example.smartcity.DataAccess.Service.ItemService;
import com.example.smartcity.Model.ApiResponse;
import com.example.smartcity.Model.Item;
import com.example.smartcity.Model.PagingResult;
import com.example.smartcity.Utilitaries.RetrofitInstance;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import org.apache.commons.io.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.smartcity.Utilitaries.App.getContext;

public class ItemRepository implements ItemDataAccess
{
    private MutableLiveData<ApiResponse<PagingResult<Item>>> itemsLive;
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
        this.internetChecking = new InternetChecking();
        this.itemPost = new MutableLiveData<>();
        this.context = context;
        this.itemsCategoryLive = new MutableLiveData<>();
        this.updateLive = new MutableLiveData<>();
        this.deleteLive = new MutableLiveData<>();
    }

    @Override
    public MutableLiveData<ApiResponse<PagingResult<Item>>> getItems() {

        if(!internetChecking.isNetworkAvailable()) {
            itemsLive.setValue(new ApiResponse(-1));
            return itemsLive;
        }
        ItemService service = RetrofitInstance.getRetrofitInstance(context).create(ItemService.class);
        Call<PagingResult<Item>> call = service.getItems();
        call.enqueue(new Callback<PagingResult<Item>>() {
            @Override
            public void onResponse(Call<PagingResult<Item>> call, Response<PagingResult<Item>> response) {
                if(response.isSuccessful())
                {
                    itemsLive.setValue(new ApiResponse(response.body()));
                }
                else
                {
                    itemsLive.setValue(new ApiResponse(response.code()));
                }
            }

            @Override
            public void onFailure(Call<PagingResult<Item>> call, Throwable t) {
                itemsLive.setValue(new ApiResponse(500));
            }
        });
        return itemsLive;
    }

    public MutableLiveData<ApiResponse<Item>> postItem(Item item) {
        if(!internetChecking.isNetworkAvailable()) {
            itemPost.setValue(new ApiResponse((-1)));
            return itemPost;
        }

        ItemService service = RetrofitInstance.getRetrofitInstance(getContext()).create(ItemService.class);

        // https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
        // use the FileUtils to get the actual file by uri

        File file = bitMapToFile(item.getBitmap());

        okhttp3.RequestBody reqFile = okhttp3.RequestBody.create(
                okhttp3.MediaType.parse(getContext().getContentResolver().getType(item.getUriPicture())),
                file
        );
        MultipartBody.Part body = MultipartBody.Part.createFormData("picture", file.getName(), reqFile);


        Call<Item> call = service.postItem(item.getTitle(), item.getDescription(), item.getPricePerDay(), item.getItemCategory(), body);
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response){
                if (response.isSuccessful()) {
                    itemPost.setValue(new ApiResponse(response.body()));
                } else {
                    Log.i("test", "" + response.code());
                    itemPost.setValue(new ApiResponse(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                itemPost.setValue(new ApiResponse(500));
            }
        });
        return itemPost;
    }

    public MutableLiveData<ApiResponse> deleteItem(int itemId)
    {
        if(!internetChecking.isNetworkAvailable()) {
            deleteLive.setValue(new ApiResponse(-1));
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
                    deleteLive.setValue(new ApiResponse(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                deleteLive.setValue(new ApiResponse(500));
            }
        });
        return deleteLive;
    }

    public MutableLiveData<ApiResponse> updateItem(Item item)
    {
        if(!internetChecking.isNetworkAvailable()) {
            updateLive.setValue(new ApiResponse(-1));
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
                    updateLive.setValue(new ApiResponse(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                updateLive.setValue(new ApiResponse(500));
            }
        });
        return updateLive;
    }


    public MutableLiveData<ApiResponse<List<Item>>> getMyItems()
    {
        if(!internetChecking.isNetworkAvailable()) {
            myItems.setValue(new ApiResponse(-1));
            return myItems;
        }
        ItemService service = RetrofitInstance.getRetrofitInstance(context).create(ItemService.class);
        Call<List<Item>> call = service.getMyItems();
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if(response.isSuccessful())
                {
                    myItems.setValue(new ApiResponse(response.body()));
                }
                else
                {
                    myItems.setValue(new ApiResponse(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                myItems.setValue(new ApiResponse(500));
            }
        });
        return myItems;
    }

    public MutableLiveData<ApiResponse<List<Item>>> getItemsByCategory(int categoryId)
    {
        if(!internetChecking.isNetworkAvailable()) {
            itemsCategoryLive.setValue(new ApiResponse(-1));
            return itemsCategoryLive;
        }
        ItemService service = RetrofitInstance.getRetrofitInstance(context).create(ItemService.class);
        Call<List<Item>> call = service.getItemsByCategory(categoryId);
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if(response.isSuccessful())
                {
                    itemsCategoryLive.setValue(new ApiResponse(response.body()));
                }
                else
                {
                    itemsCategoryLive.setValue(new ApiResponse(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                itemsCategoryLive.setValue(new ApiResponse(500));
            }
        });
        return itemsCategoryLive;

    }

    public File bitMapToFile(Bitmap bitmap) {

        File f = new File(getContext().getCacheDir(), "mypic");
        try {
            f = new File(getContext().getCacheDir(), "mypic");
            f.createNewFile();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 0 /*ignored for PNG*/, bos);
            byte[] bitmapdata = bos.toByteArray();

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(f);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                fos.write(bitmapdata);
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        return f;

    }


}
