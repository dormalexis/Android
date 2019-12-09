package com.example.smartcity.ViewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartcity.Model.Item;
import com.example.smartcity.Utilitaries.ProviderConverter;
import com.example.smartcity.ViewModel.ItemDataAccess;

import java.util.ArrayList;
import java.io.*;
import java.net.*;

public class ItemDAO extends ViewModel implements ItemDataAccess {
    MutableLiveData<ArrayList<Item>> items;
    public MutableLiveData<ArrayList<Item>> getAllItems()
    {
        try {

            URL url = new URL("https://locappapi.azurewebsites.net/api/Item");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String stringJSON = "", line;
            while((line = buffer.readLine()) != null)
            {
                builder.append(line);
            }
            buffer.close();
            stringJSON = builder.toString();
            return ProviderConverter.jsonToItems(stringJSON);
        }
        catch(Exception e)
        {
            Log.e("blabla", e.getMessage());
        }

        return new MutableLiveData<ArrayList<Item>>;
    }
}
