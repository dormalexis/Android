package com.example.smartcity.Utilitaries;

import android.content.Context;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit;
    private static final String url = "https://192.168.1.49:45456/api/";

    public static Retrofit getRetrofitInstance(Context context) {

        if (retrofit == null) {
            OkHttpClient client = UnsafeOkHttpClient.getUnsafeOkHttpClient(context);
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()))
                    .client(client)
                    .build();

        }
        return retrofit;
    }
}
