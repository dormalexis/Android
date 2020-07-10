package com.example.smartcity.DataAccess;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

import static com.example.smartcity.Utilitaries.App.getContext;


public class InternetChecking {

        public InternetChecking() {
        }

        public Boolean isNetworkAvailable() {
            ConnectivityManager connMgr = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            return (networkInfo != null && networkInfo.isConnected());
        }
}

