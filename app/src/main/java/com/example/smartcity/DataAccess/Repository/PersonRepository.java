package com.example.smartcity.DataAccess.Repository;

import android.content.Context;
import android.util.Log;

import com.example.smartcity.DataAccess.InternetChecking;
import com.example.smartcity.DataAccess.Service.ItemService;
import com.example.smartcity.DataAccess.Service.PersonService;
import com.example.smartcity.Model.Item;
import com.example.smartcity.Model.Person;
import com.example.smartcity.Utilitaries.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonRepository implements PersonDataAccess {

    private Context context;
    private InternetChecking internetChecking;

    public PersonRepository(Context context)
    {
        this.context = context;
    }
    public void postPerson(Person person)
    {
        if(!internetChecking.isNetworkAvailable()) {} // Todo : Renvoie erreur pas de connection
        PersonService service = RetrofitInstance.getRetrofitInstance(context).create(PersonService.class);
        Call<Integer> call = service.postPerson(person);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.i("postOk", response.body().toString());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.i("postFailed", "Post person failed");
            }
        });
    }
}
