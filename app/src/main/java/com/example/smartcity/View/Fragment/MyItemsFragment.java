package com.example.smartcity.View.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import java.util.List;

public class MyItemsFragment extends Fragment implements ItemAdapter.OnItemListener {


    private ArrayList<Item> itemsArray = new ArrayList<>();
    private ItemViewModel itemModel;
    private ItemAdapter adapter;
    private RecyclerView recyclerView;
    private List<Item> itemList;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        adapter = new ItemAdapter(this);
    }

    public MyItemsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myitems, container, false);
        recyclerView = view.findViewById(R.id.myItemRV);  //TODO : changer avec @bind
        itemModel = new ItemViewModel(getContext());

        itemModel.getMyItems().observe(this, items -> {
            adapter.setItems(items.getObject());
            itemList = items.getObject();
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            if(items.getObject()==null)
            {
                Toast.makeText(getContext(),R.string.empty,Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    @Override
    public void onItemClick(int position) {
        Item itemSelected = itemList.get(position);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, new UpdateItemFragment(itemSelected));
        transaction.addToBackStack(new HomeFragment().getClass().getName());
        transaction.commit();
    }

}
