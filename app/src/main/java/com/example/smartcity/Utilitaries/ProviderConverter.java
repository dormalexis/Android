package com.example.smartcity.Utilitaries;

import androidx.lifecycle.MutableLiveData;

import com.example.smartcity.Model.Item;
import com.google.gson.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProviderConverter {
    public static ArrayList<Item> jsonToItems(String stringJSON) {
        ArrayList<Item> items = new ArrayList<Item>();
        JSONObject jsonItem;
        try {
            JSONArray jsonArray = new JSONArray(stringJSON);
            for(int i = 0; i < jsonArray.length(); i++)
            {
                jsonItem = jsonArray.getJSONObject(i);
                items.add(new GsonBuilder().create().fromJson(jsonItem.toString(), Item.class));
            }
        }
        catch(Exception e)
        {
        }
        return items;
    }
}
