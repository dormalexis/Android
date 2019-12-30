package com.example.smartcity.Exception;

import android.content.Context;

import com.example.smartcity.R;


public class PasswordException extends Exception{
    private Context context;
    public PasswordException(Context context) {
        this.context = context;
    }

    @Override
    public String getMessage()
    {
        return context.getString(R.string.passwordException);
    }
}
