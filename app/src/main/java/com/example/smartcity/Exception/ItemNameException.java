package com.example.smartcity.Exception;

import android.content.Context;
import android.content.res.Resources;
import android.telephony.mbms.DownloadProgressListener;

import com.example.smartcity.R;
import com.example.smartcity.Utilitaries.App;

public class ItemNameException extends Exception {
    public ItemNameException() {
    }

    @Override
    public String getMessage()
    {
        return App.getContext().getResources().getString(R.string.itemNameException);
    }

}
