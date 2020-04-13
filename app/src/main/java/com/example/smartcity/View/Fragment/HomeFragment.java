package com.example.smartcity.View.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.DataAccess.ViewModel.CategoryViewModel;
import com.example.smartcity.DataAccess.ViewModel.FavoriteViewModel;
import com.example.smartcity.DataAccess.ViewModel.PictureViewModel;
import com.example.smartcity.Model.ApiResponse;
import com.example.smartcity.Model.ItemCategory;
import com.example.smartcity.Model.Picture;
import com.example.smartcity.View.MapsActivity;
import com.example.smartcity.View.RecyclerView.ItemAdapter;
import com.example.smartcity.Model.Item;
import com.example.smartcity.R;
import com.example.smartcity.DataAccess.ViewModel.ItemViewModel;
import com.example.smartcity.View.RecyclerView.ItemViewHolder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class HomeFragment extends Fragment implements ItemAdapter.OnItemListener {

    private ItemViewModel itemModel;
    private ItemAdapter adapter;
    private CategoryViewModel categoryModel;
    private List<Item> itemList;

    @BindView(R.id.map_button)
    FloatingActionButton mapButton;

    @BindView(R.id.homeRV)
    RecyclerView recyclerView;


    @Override
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        adapter = new ItemAdapter(this);
        itemModel = new ItemViewModel(getContext());
    }

    public HomeFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        ButterKnife.bind(this, view);
        mapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MapsActivity.class));
            }
        });

        recyclerView.removeAllViews();
        itemModel.getItems().observe(this,items -> {
            if(items.isErrorDetected())
            {
                Toast.makeText(getContext(),items.getErrorCode().getMessage(),Toast.LENGTH_LONG).show();
            }
            else
            {
                adapter.setItems(items.getObject());
                itemList = items.getObject();
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                if(items.getObject()== null)
                {
                    Toast.makeText(getContext(),R.string.empty,Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    @Override
    public void onItemClick(int position) {
        Item itemSelected = itemList.get(position);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, new ItemDetailsFragment(itemSelected));
        transaction.addToBackStack(new HomeFragment().getClass().getName());
        transaction.commit();
    }

}
