package com.example.smartcity.DataAccess.Service;

import com.example.smartcity.Model.Item;
import com.example.smartcity.Model.Person;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PersonService {

    @POST("Person")
    Call<Integer> postPerson(@Body Person person);
}
