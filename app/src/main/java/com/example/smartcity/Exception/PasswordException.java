package com.example.smartcity.Exception;

import android.content.Context;
import android.content.res.Resources;

import com.example.smartcity.R;
import com.example.smartcity.Utilitaries.App;


public class PasswordException extends Exception{

    public PasswordException() {

    }

    @Override
    public String getMessage()
    {
        return App.getContext().getResources().getString(R.string.passwordException);
    }
}
