package com.example.smartcity.View.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.View.RecyclerView.ItemAdapter;
import com.example.smartcity.Model.Item;
import com.example.smartcity.R;
import com.example.smartcity.DataAccess.ViewModel.ItemViewModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ArrayList<Item> items = new ArrayList<>();
    ItemViewModel itemModel;
    private ItemAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        adapter = new ItemAdapter();
    }

    public HomeFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        recyclerView = view.findViewById(R.id.homeRV);
        itemModel = new ItemViewModel();

        itemModel.getItems().observe(this,items -> {
            adapter.setItems(items);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        });
        return view;
    }
}
