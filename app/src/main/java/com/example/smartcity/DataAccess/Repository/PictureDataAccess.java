package com.example.smartcity.DataAccess.Repository;

import androidx.lifecycle.MutableLiveData;

import com.example.smartcity.Model.ApiResponse;
import com.example.smartcity.Model.Picture;

public interface PictureDataAccess {
    MutableLiveData<ApiResponse> postPicture(Picture picture);
}
