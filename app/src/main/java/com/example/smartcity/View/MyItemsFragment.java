package com.example.smartcity.View;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.ItemService;
import com.example.smartcity.R;
import com.example.smartcity.RetrofitItem;
import com.example.smartcity.ItemAdapter;
import com.example.smartcity.Model.Item;
import com.example.smartcity.ViewModel.ItemRepository;
import com.example.smartcity.ViewModel.ItemViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyItemsFragment extends Fragment {

    ArrayList<Item> items = new ArrayList<>();
    ItemViewModel itemModel;
    private ItemAdapter adapter;
    private RecyclerView recyclerView;

    public MyItemsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        adapter = new ItemAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myitems,container,false);
        recyclerView = view.findViewById(R.id.recyclerView);
        itemModel = new ItemViewModel();

        itemModel.getItems().observe(this,items -> {
            adapter.setItems(items);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        });
        return view;

    }



}
