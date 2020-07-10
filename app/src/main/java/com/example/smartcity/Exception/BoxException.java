package com.example.smartcity.Exception;
import android.content.res.Resources;

import com.example.smartcity.R;

public class BoxException extends Exception{

    public BoxException() {

    }

    @Override
    public String getMessage()
    {
        return Resources.getSystem().getString(R.string.boxException);
    }
}
