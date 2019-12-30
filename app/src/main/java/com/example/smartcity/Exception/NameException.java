package com.example.smartcity.Exception;

import android.content.Context;
import android.content.res.Resources;

import com.example.smartcity.R;
import com.example.smartcity.Utilitaries.App;

public class NameException extends Exception{


    public NameException() {

    }

    @Override
    public String getMessage()
    {
        return App.getContext().getResources().getString(R.string.nameException);
    }
}
