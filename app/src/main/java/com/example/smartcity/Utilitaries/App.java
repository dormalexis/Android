package com.example.smartcity.Utilitaries;
import com.stripe.android.PaymentConfiguration;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        PaymentConfiguration.init(
                getApplicationContext(),
                "pk_test_mRwq4CEk7BGNWOyGSfhw9IOM00odkD2WRO"
        );
    }

    public static Context getContext(){
        return mContext;
    }
}
