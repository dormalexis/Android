package com.example.smartcity.Exception;

import android.content.Context;

import com.example.smartcity.R;

public class FirstNameException extends Exception {
    private Context context;
    public FirstNameException(Context context) {
        this.context = context;
    }

    @Override
    public String getMessage()
    {
        return context.getString(R.string.firstNameException);
    }
}
