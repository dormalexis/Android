package com.example.smartcity.DataAccess.Service;

import com.example.smartcity.Model.Item;
import com.example.smartcity.Model.Person;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PersonService {

    @POST("Person")
    Call<Person> postPerson(@Body Person person);

    @GET("Person/{id}")
    Call<Person> getPerson(@Path("id") Integer personId);

    @PUT("Person/{id}")
    Call<Void> updatePerson(@Path("id") Integer personId, @Body Person person);
}
