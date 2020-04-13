package com.example.smartcity.Utilitaries;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences
{


    public static void saveToken(String token, Context context)
    {
        SharedPreferences settings = context.getSharedPreferences("connection", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("token", token);
        editor.apply();
    }

    public static String getToken(Context context)
    {
        return context.getSharedPreferences("connection", Context.MODE_PRIVATE).getString("token", new String());
    }

    public static void signOutToken(Context context)
    {
        SharedPreferences settings = context.getSharedPreferences("connection", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.apply();
    }

}
