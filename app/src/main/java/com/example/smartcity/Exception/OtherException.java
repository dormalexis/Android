package com.example.smartcity.Exception;

import com.example.smartcity.R;
import com.example.smartcity.Utilitaries.App;

public class OtherException extends Exception {
    public OtherException(){}

    @Override
    public String getMessage()
    {
        return App.getContext().getResources().getString(R.string.OTHERERROR);
    }
}
