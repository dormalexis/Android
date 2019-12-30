package com.example.smartcity.DataAccess.Repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.cloudinary.Api;
import com.example.smartcity.DataAccess.InternetChecking;
import com.example.smartcity.DataAccess.Service.ItemService;
import com.example.smartcity.DataAccess.Service.PersonService;
import com.example.smartcity.Model.ApiResponse;
import com.example.smartcity.Model.Item;
import com.example.smartcity.Model.Person;
import com.example.smartcity.Utilitaries.ApiCodeTrad;
import com.example.smartcity.Utilitaries.ApiResponseErrorCode;
import com.example.smartcity.Utilitaries.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonRepository implements PersonDataAccess {

    private Context context;
    private InternetChecking internetChecking;
    private MutableLiveData<ApiResponse> personneLive;

    public PersonRepository(Context context)
    {
        this.personneLive = new MutableLiveData<>();
        this.context = context;
        this.internetChecking = new InternetChecking(context);
    }
    public MutableLiveData<ApiResponse> postPerson(Person person)
    {
        if(!internetChecking.isNetworkAvailable()) {
            personneLive.setValue(new ApiResponse(ApiResponseErrorCode.NETWORKFAIL));
            return personneLive;
        }
        PersonService service = RetrofitInstance.getRetrofitInstance(context).create(PersonService.class);
        Call<Integer> call = service.postPerson(person);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful())
                {
                    personneLive.setValue(new ApiResponse());
                }
                else
                {
                    personneLive.setValue(new ApiResponse(ApiCodeTrad.codeErrorToApiResponse(response.code())));
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                personneLive.setValue(new ApiResponse(ApiResponseErrorCode.SERVEURERROR));
            }
        });
        return personneLive;
    }
}
