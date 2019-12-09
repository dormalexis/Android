package com.example.smartcity.View;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.ViewModel.ItemDAO;
import com.example.smartcity.ViewModel.ItemDataAccess;
import com.example.smartcity.ItemAdapter;
import com.example.smartcity.Model.Item;

import java.util.ArrayList;

public class MyItemsFragment extends Fragment {

    ItemDAO itemDataAccess;
    ArrayList<Item> items = new ArrayList<>();


    public MyItemsFragment() {
        new LoadItems().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        items = itemDataAccess.getAllItems();
        RecyclerView rv = new RecyclerView(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(new ItemAdapter(items));
        return rv;
    }

    private class LoadItems extends AsyncTask<String, Void, ArrayList<Item>>
    {

        @Override
        protected ArrayList<Item> doInBackground(String... strings) {
            ItemDataAccess itemDataAccess = new ItemDAO();
            ArrayList<Item> items = new ArrayList<>();
            try {
                items = itemDataAccess.getAllItems();
            }
            catch(Exception e)
            {
                System.out.println("Test");
            }
            return items;
        }

        @Override
        protected void onPostExecute(ArrayList<Item> items)
        {

        }
    }


}
