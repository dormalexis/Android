package com.example.smartcity.View.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.smartcity.DataAccess.ViewModel.RentalViewModel;
import com.example.smartcity.R;
import com.example.smartcity.View.RecyclerView.ItemAdapter;
import com.example.smartcity.View.RecyclerView.RentalAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyRentalsFragment extends Fragment {

    @BindView(R.id.myRentalsRecyclerView)
    RecyclerView myRentalsRecyclerView;

    private RentalViewModel rentalViewModel;
    private RentalAdapter rentalAdapter;


    public MyRentalsFragment(){ }

    @Override
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        rentalAdapter = new RentalAdapter();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_rentals,container,false);
        ButterKnife.bind(this, view);
        rentalViewModel = new RentalViewModel(getContext());
        rentalViewModel.getRentals().observe(this, rentals -> {
            if(rentals.isErrorDetected())
            {
                Toast.makeText(getContext(),rentals.getErrorCode().getMessage(),Toast.LENGTH_LONG);
            }
            else {
                rentalAdapter.setRentals(rentals.getObject());
                myRentalsRecyclerView.setAdapter(rentalAdapter);
                myRentalsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });
        return view;
    }
}
