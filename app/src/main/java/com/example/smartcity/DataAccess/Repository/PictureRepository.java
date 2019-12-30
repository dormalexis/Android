package com.example.smartcity.DataAccess.Repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.smartcity.DataAccess.InternetChecking;
import com.example.smartcity.DataAccess.Service.ItemService;
import com.example.smartcity.DataAccess.Service.PictureService;
import com.example.smartcity.Model.ApiResponse;
import com.example.smartcity.Model.Picture;
import com.example.smartcity.Utilitaries.ApiCodeTrad;
import com.example.smartcity.Utilitaries.ApiResponseErrorCode;
import com.example.smartcity.Utilitaries.RetrofitInstance;

import java.util.MissingFormatArgumentException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PictureRepository implements PictureDataAccess {
    private Context context;
    private InternetChecking internetChecking;
    private MutableLiveData<ApiResponse> pictureLive;

    public PictureRepository(Context context)
    {
        this.context = context;
        this.internetChecking = new InternetChecking(context);
        this.pictureLive = new MutableLiveData<>();
    }

    public MutableLiveData<ApiResponse> postPicture(Picture picture) {
        if(!internetChecking.isNetworkAvailable()) {
            pictureLive.setValue(new ApiResponse(ApiResponseErrorCode.NETWORKFAIL));
        }
        PictureService service = RetrofitInstance.getRetrofitInstance(context).create(PictureService.class);
        Call<Void> call = service.postPicture(picture);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful())
                {
                    pictureLive.setValue(new ApiResponse());
                }
                else
                {
                    pictureLive.setValue(new ApiResponse(ApiCodeTrad.codeErrorToApiResponse(response.code())));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                pictureLive.setValue(new ApiResponse(ApiResponseErrorCode.SERVEURERROR));
            }
        });
        return pictureLive;
    }
}
