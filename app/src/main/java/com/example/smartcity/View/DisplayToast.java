package com.example.smartcity.View;

import android.widget.Toast;

import com.example.smartcity.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

import static com.example.smartcity.Utilitaries.App.getContext;

public class DisplayToast {

    private static Map<Integer, Integer> StatusCode = new HashMap<Integer, Integer>() {
    {
        put(-1, R.string.NETWORKFAIL);
        put(404, R.string.NOTFOUND);
        put(401, R.string.UNAUTHORIZED);
        put(404, R.string.NOTFOUND);
        put(500, R.string.INTERNALSERVERERROR);
        put(503, R.string.SERVICEUNAVAILABLE);

    }
    };

    public static void display(int errorCode) {
        Toast.makeText(getContext(), getContext().getString(StatusCode.get(errorCode)), Toast.LENGTH_SHORT).show();
    }

    public static void displaySpecific(int ressourceId) {
        Toast.makeText(getContext(), getContext().getString(ressourceId), Toast.LENGTH_SHORT).show();
    }
}
