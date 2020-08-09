package com.example.smartcity.Utilitaries;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;

import static com.example.smartcity.Utilitaries.App.getContext;

public class Preferences
{

    public static void saveToken(String token)
    {
        SharedPreferences settings = getContext().getSharedPreferences("connection", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("token", token);
        editor.apply();
    }

    public static String getToken()
    {
        return getContext().getSharedPreferences("connection", Context.MODE_PRIVATE).getString("token", "");
    }

    public static void signOutToken()
    {
        SharedPreferences settings = getContext().getSharedPreferences("connection", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.apply();
    }

    public static Integer getUserId() {
        Log.i("Alexis", new JWT(getToken()).getClaim("userId").asInt() + "" );
        return new JWT(getToken()).getClaim("userId").asInt();

    }

}
