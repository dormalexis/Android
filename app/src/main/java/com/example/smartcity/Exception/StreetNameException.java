package com.example.smartcity.Exception;

import android.content.Context;

import com.example.smartcity.R;

public class StreetNameException extends Exception {
    private Context context;
    public StreetNameException(Context context) {
        this.context = context;
    }

    @Override
    public String getMessage()
    {
        return context.getString(R.string.streetNameException);
    }
}
