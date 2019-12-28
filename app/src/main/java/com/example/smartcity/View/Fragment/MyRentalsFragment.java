package com.example.smartcity.View.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartcity.DataAccess.ViewModel.RentalViewModel;
import com.example.smartcity.R;
import com.example.smartcity.View.RecyclerView.RentalAdapter;

import butterknife.BindView;

public class MyRentalsFragment extends Fragment {

    @BindView(R.id.myRentalsRecyclerView)
    RecyclerView myRentalsRecyclerView;

    private RentalViewModel rentalViewModel;
    private RentalAdapter rentalAdapter;


    public MyRentalsFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_rentals,container,false);

        rentalViewModel = new RentalViewModel(getContext());
        rentalViewModel.getRentals().observe(this, rentals -> {
            rentalAdapter.setRentals(rentals);
            myRentalsRecyclerView.setAdapter(rentalAdapter);
        });
        return view;
    }
}
