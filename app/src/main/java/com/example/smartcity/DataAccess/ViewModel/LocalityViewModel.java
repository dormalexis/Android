package com.example.smartcity.DataAccess.ViewModel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartcity.DataAccess.Repository.LocalityRepository;
import com.example.smartcity.Model.Locality;

import java.util.List;

public class LocalityViewModel extends ViewModel {

    private LocalityRepository localityRepository;
    private MutableLiveData<List<Locality>> localities;
    private Context context;

    public LocalityViewModel(Context context) {
        this.context = context;
        localityRepository = new LocalityRepository(context);
    }

    public MutableLiveData<List<Locality>> getLocalities() {
        localities = localityRepository.getLocalities();
        return localities;
    }
}
