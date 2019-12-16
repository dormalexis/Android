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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.View.RecyclerView.ItemAdapter;
import com.example.smartcity.Model.Item;
import com.example.smartcity.R;
import com.example.smartcity.DataAccess.ViewModel.ItemViewModel;
import com.example.smartcity.View.RecyclerView.ItemViewHolder;

import java.io.Console;
import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class HomeFragment extends Fragment implements ItemAdapter.OnItemListener {

    ArrayList<Item> items = new ArrayList<>();
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
    public void onItemClick(int postition) {
        //Item item = items.get(postition);
        //La liste des items est vide mais sinon avec cette ligne on peut retrouver l'item sur lequel
        //on a cliqué
        //Puis on peut lancer une autre Activité/lister pour afficher les détails
        Toast.makeText(getContext(), "click " + postition, Toast.LENGTH_SHORT).show();

    }
}
