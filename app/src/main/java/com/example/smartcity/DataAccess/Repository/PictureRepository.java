package com.example.smartcity.DataAccess.Repository;

import android.content.Context;
import android.util.Log;

import com.example.smartcity.DataAccess.InternetChecking;
import com.example.smartcity.DataAccess.Service.ItemService;
import com.example.smartcity.DataAccess.Service.PictureService;
import com.example.smartcity.Model.Picture;
import com.example.smartcity.Utilitaries.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PictureRepository implements PictureDataAccess {
    private Context context;
    private InternetChecking internetChecking;

    public PictureRepository(Context context)
    {

        this.context = context;
        this.internetChecking = new InternetChecking(context);
    }

    public void postPicture(Picture picture) {
        if(!internetChecking.isNetworkAvailable()) {} // Todo : Renvoie erreur pas de connection
        PictureService service = RetrofitInstance.getRetrofitInstance(context).create(PictureService.class);
        Call<Integer> call = service.postPicture(picture);
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
