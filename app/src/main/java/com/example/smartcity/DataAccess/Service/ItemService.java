package com.example.smartcity.DataAccess.Service;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import com.example.smartcity.Model.Item;
import com.example.smartcity.Model.PagingResult;
import com.squareup.okhttp.RequestBody;

import java.util.List;

public interface ItemService {

    @GET("Item")
    Call<PagingResult<Item>> getItems();

    @GET("Item/byCategory/{id}")
    Call<List<Item>> getItemsByCategory(@Path("id") int id);

    @GET("Item/myItems")
    Call<List<Item>> getMyItems();

    @PUT("Item/{id}")
    Call<Void> updateItem(@Path("id") int id,@Body Item item);

    @DELETE("Item/{id}")
    Call<Void> deleteItem(@Path("id") int id);

    @Multipart
    @POST("Item")
    Call<Item> postItem(
            @Part("title") String title,
            @Part("description") String description,
            @Part("pricePerDay") Double pricePerDay,
            @Part("itemCategory") Integer itemCategory,
            @Part MultipartBody.Part picture);

}
