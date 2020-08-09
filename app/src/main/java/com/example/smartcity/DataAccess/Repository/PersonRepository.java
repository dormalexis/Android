package com.example.smartcity.DataAccess.Repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import com.example.smartcity.DataAccess.InternetChecking;
import com.example.smartcity.DataAccess.Service.PersonService;
import com.example.smartcity.Model.ApiResponse;
import com.example.smartcity.Model.Person;
import com.example.smartcity.Utilitaries.Preferences;
import com.example.smartcity.Utilitaries.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonRepository implements PersonDataAccess {

    private Context context;
    private InternetChecking internetChecking;
    private MutableLiveData<ApiResponse> personneLive;
    private MutableLiveData<ApiResponse<Person>> myInformationsLive;

    public PersonRepository(Context context)
    {
        this.personneLive = new MutableLiveData<>();
        this.myInformationsLive = new MutableLiveData<>();
        this.context = context;
        this.internetChecking = new InternetChecking();
    }
    public MutableLiveData<ApiResponse> postPerson(Person person)
    {
        if(!internetChecking.isNetworkAvailable()) {
            personneLive.setValue(new ApiResponse(-1));
            return personneLive;
        }
        PersonService service = RetrofitInstance.getRetrofitInstance(context).create(PersonService.class);
        Call<Person> call = service.postPerson(person);
        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                if(response.isSuccessful())
                {
                    personneLive.setValue(new ApiResponse(response.body()));
                    Log.i("Alexis", String.valueOf(response.code()));
                }

                else
                {
                    personneLive.setValue(new ApiResponse(response.code()));
                    Log.i("Alexis", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {

                personneLive.setValue(new ApiResponse(500));
            }
        });
        return personneLive;
    }

    public MutableLiveData<ApiResponse> updatePerson(Person person)
    {
        if(!internetChecking.isNetworkAvailable()) {
            personneLive.setValue(new ApiResponse(-1));
            return personneLive;
        }
        PersonService service = RetrofitInstance.getRetrofitInstance(context).create(PersonService.class);
        Call<Void> call = service.updatePerson(Preferences.getUserId(), person);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful())
                {
                    personneLive.setValue(new ApiResponse(response.code()));
                    Log.i("Alexis", String.valueOf(response.code()));
                }

                else
                {
                    personneLive.setValue(new ApiResponse(response.code()));
                    Log.i("Alexis", String.valueOf(response.message()));
                    Log.i("Alexis", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                personneLive.setValue(new ApiResponse(500));
            }
        });
        return personneLive;
    }

    public MutableLiveData<ApiResponse<Person>> getPerson(Integer personId)
    {
        if(!internetChecking.isNetworkAvailable()) {
            myInformationsLive.setValue(new ApiResponse(-1));
            return myInformationsLive;
        }
        PersonService service = RetrofitInstance.getRetrofitInstance(context).create(PersonService.class);
        Call<Person> call = service.getPerson(personId);
        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                if(response.isSuccessful())
                {
                    myInformationsLive.setValue(new ApiResponse(response.body()));
                    Log.i("Alexis", String.valueOf(response.code()));
                }

                else
                {
                    myInformationsLive.setValue(new ApiResponse(response.code()));
                    Log.i("Alexis", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {

                myInformationsLive.setValue(new ApiResponse(500));
            }
        });
        return myInformationsLive;
    }
}
