package com.example.smartcity.View.Fragment;
import android.content.Intent;
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

import com.example.smartcity.DataAccess.ViewModel.CategoryViewModel;
import com.example.smartcity.DataAccess.ViewModel.FavoriteViewModel;
import com.example.smartcity.DataAccess.ViewModel.ItemViewModel;
import com.example.smartcity.Model.Item;
import com.example.smartcity.R;
import com.example.smartcity.View.MapsActivity;
import com.example.smartcity.View.RecyclerView.ItemAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteFragment extends Fragment implements ItemAdapter.OnItemListener {

    private FavoriteViewModel favoriteViewModel;
    private ItemAdapter adapter;
    private List<Item> itemList;

    @BindView(R.id.favoriteRV)
    RecyclerView recyclerView;


    @Override
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        adapter = new ItemAdapter(this);
        favoriteViewModel = new FavoriteViewModel();
    }

    public FavoriteFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite,container,false);
        ButterKnife.bind(this, view);

        recyclerView.removeAllViews();
        favoriteViewModel.getFavorites().observe(this,favorites -> {
            if(favorites.isErrorDetected())
            {
                Toast.makeText(getContext(),favorites.getErrorCode().getMessage(),Toast.LENGTH_LONG).show();
            }
            else
            {
                adapter.setItems(favorites.getObject());
                itemList = favorites.getObject();
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                if(favorites.getObject()== null)
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
