package com.example.smartcity.View.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.DataAccess.ViewModel.FavoriteViewModel;
import com.example.smartcity.Model.Item;
import com.example.smartcity.R;
import com.example.smartcity.View.DisplayToast;
import com.example.smartcity.View.RecyclerView.ItemAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteFragment extends Fragment implements ItemAdapter.OnItemListener {

    private FavoriteViewModel favoriteViewModel;
    private ItemAdapter adapter;
    private List<Item> itemList;

    @BindView(R.id.favoriteRV)
    RecyclerView recyclerView;

    @BindView(R.id.indeterminateBar)
    ProgressBar progressBar;

    @BindView(R.id.nothing)
    TextView nothing;




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
            progressBar.setVisibility(View.GONE);
            if(favorites.isErrorDetected())
            {
                DisplayToast.display(favorites.getErrorCode());
            }
            else
            {
                adapter.setItems(favorites.getObject());
                itemList = favorites.getObject();
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                if(favorites.getObject().size() == 0)
                {
                    nothing.setVisibility(View.VISIBLE);
                    // TODO Ajouter dans le layout et changer la visibility gone à visible
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
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
