package com.example.smartcity.View.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
import com.example.smartcity.DataAccess.ViewModel.ItemViewModel;
import com.example.smartcity.Model.Item;
import com.example.smartcity.R;
import com.example.smartcity.View.RecyclerView.ItemAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static androidx.fragment.app.FragmentTransaction.TRANSIT_ENTER_MASK;

public class HomeFragment extends Fragment implements ItemAdapter.OnItemListener {

    private ItemViewModel itemModel;
    private ItemAdapter adapter;
    private CategoryViewModel categoryModel;
    private List<Item> itemList;

    @BindView(R.id.homeRV)
    RecyclerView recyclerView;


    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        adapter = new ItemAdapter(this);
        itemModel = new ItemViewModel(getContext());
    }

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        recyclerView.removeAllViews();
        itemModel.getItems().observe(this, result -> {
            if (result.isErrorDetected()) {
                Toast.makeText(getContext(), "" + result.getErrorCode(), Toast.LENGTH_LONG).show();
            } else {
                Log.i("Alexis", result.isErrorDetected() + "");
                adapter.setItems(result.getObject().getResult());
                itemList = result.getObject().getResult();
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                if (result.getObject() == null) {
                    Toast.makeText(getContext(), R.string.empty, Toast.LENGTH_LONG).show();
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
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.addToBackStack(new HomeFragment().getClass().getName());
        transaction.commit();
    }

}
