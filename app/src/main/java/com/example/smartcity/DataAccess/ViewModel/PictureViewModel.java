package com.example.smartcity.DataAccess.ViewModel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.smartcity.DataAccess.Repository.PictureRepository;
import com.example.smartcity.Model.Picture;

public class PictureViewModel extends ViewModel {
    private PictureRepository repository;

    public PictureViewModel(Context context)
    {
        this.repository = new PictureRepository(context);
    }

    public void postPicture(Picture picture){
        repository.postPicture(picture);
    }
}
