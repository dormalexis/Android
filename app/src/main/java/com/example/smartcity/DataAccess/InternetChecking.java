package com.example.smartcity.DataAccess;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;


public class InternetChecking {
        private Context context;

        public InternetChecking(Context context) {
            this.context = context;
        }

        public Boolean isNetworkAvailable() {
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            return (networkInfo != null && networkInfo.isConnected());
        }
}

