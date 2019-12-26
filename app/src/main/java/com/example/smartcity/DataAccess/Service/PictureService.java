package com.example.smartcity.DataAccess.Service;

import com.example.smartcity.Model.Picture;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PictureService
{
    @POST("Picture")
    Call<Integer> postPicture(@Body Picture picture);
}
