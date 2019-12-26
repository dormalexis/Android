package com.example.smartcity.View.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.DataAccess.ViewModel.ItemViewModel;
import com.example.smartcity.Model.Item;
import com.example.smartcity.R;
import com.example.smartcity.View.RecyclerView.ItemAdapter;

import java.util.ArrayList;

public class MyItemsFragment extends Fragment {


    private ArrayList<Item> itemsArray = new ArrayList<>();
    private ItemViewModel itemModel;
    private ItemAdapter adapter;
    private RecyclerView recyclerView;

    public MyItemsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        adapter = new ItemAdapter(this::onItemClick);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myitems, container, false);
        recyclerView = view.findViewById(R.id.myItemRV);
        itemModel = new ItemViewModel(getContext());

        itemModel.getMyItems().observe(this, items -> {
            adapter.setItems(items);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        });
        return view;
    }

    public void onItemClick(int position) {
        Item itemSelected = itemModel.getItems().getValue().get(position);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, new ItemDetailsFragment(itemSelected));
        transaction.addToBackStack(new HomeFragment().getClass().getName());
        transaction.commit();
    }

}
