package com.example.smartcity.View.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.View.RecyclerView.ItemAdapter;
import com.example.smartcity.Model.Item;
import com.example.smartcity.R;
import com.example.smartcity.DataAccess.ViewModel.ItemViewModel;
import com.example.smartcity.View.RecyclerView.ItemViewHolder;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class HomeFragment extends Fragment implements ItemAdapter.OnItemListener {

    ArrayList<Item> itemsArray = new ArrayList<>();
    ItemViewModel itemModel;
    private ItemAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        adapter = new ItemAdapter(this);
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

    @Override
    public void onItemClick(int position) {
        Item itemSelected = itemModel.getItems().getValue().get(position);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, new ItemDetailsFragment(itemSelected));
        transaction.commit();
    }
}
