package com.example.smartcity.Exception;

import android.content.Context;

import com.example.smartcity.R;

public class PhoneNumberException extends Exception {
    private Context context;
    public PhoneNumberException(Context context) {
        this.context = context;
    }

    @Override
    public String getMessage()
    {
        return context.getString(R.string.phoneNumberException);
    }
}
