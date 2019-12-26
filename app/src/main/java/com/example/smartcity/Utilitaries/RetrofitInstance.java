package com.example.smartcity.Utilitaries;

import android.content.Context;

import java.io.IOException;
import java.net.URL;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

        private static Retrofit retrofit;
        private static final String url = "https://locappapi.azurewebsites.net/api/";

        public static Retrofit getRetrofitInstance(Context context) {

            if (retrofit == null) {
                OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Interceptor.Chain chain) throws IOException {
                        Request newRequest  = chain.request().newBuilder()
                                .addHeader("Authorization", "Bearer " + Preferences.getToken(context))
                                .build();
                        return chain.proceed(newRequest);
                    }
                }).build();
                retrofit = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build();

            }
            return retrofit;
        }
}
