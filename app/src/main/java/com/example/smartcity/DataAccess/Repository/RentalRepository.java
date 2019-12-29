package com.example.smartcity.DataAccess.Repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smartcity.DataAccess.Service.ItemService;
import com.example.smartcity.DataAccess.Service.RentalService;
import com.example.smartcity.Model.Item;
import com.example.smartcity.Model.Rental;
import com.example.smartcity.Utilitaries.RetrofitInstance;

import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RentalRepository implements RentalDataAccess {

    private MutableLiveData<List<Rental>> rentalsLive;
    private Context context;

    public RentalRepository(Context context)
    {
        this.rentalsLive = new MutableLiveData<>();
        this.context = context;
    }

    public MutableLiveData<List<Rental>> getRentals() {
        RentalService service = RetrofitInstance.getRetrofitInstance(context).create(RentalService.class);
        Call<List<Rental>> call = service.getRentals();
        call.enqueue(new Callback<List<Rental>>() {
            @Override
            public void onResponse(Call<List<Rental>> call, Response<List<Rental>> response) {
                rentalsLive.setValue(response.body());

            }

            @Override
            public void onFailure(Call<List<Rental>> call, Throwable t) {
                Log.i("home", t.getMessage());
            }
        });
        return rentalsLive;
    }

    public LiveData<List<Rental>> getValidations()
    {
        RentalService service = RetrofitInstance.getRetrofitInstance(context).create(RentalService.class);
        Call<List<Rental>> call = service.getValidations();
        call.enqueue(new Callback<List<Rental>>() {
            @Override
            public void onResponse(Call<List<Rental>> call, Response<List<Rental>> response) {
                rentalsLive.setValue(response.body());

            }

            @Override
            public void onFailure(Call<List<Rental>> call, Throwable t) {
                Log.i("home", t.getMessage());
            }
        });
        return rentalsLive;

    }
}
