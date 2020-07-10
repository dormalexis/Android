package com.example.smartcity.View.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.DataAccess.ViewModel.RentalViewModel;
import com.example.smartcity.R;
import com.example.smartcity.View.RecyclerView.RentalAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RenterRentalsHistoric extends Fragment {

    @BindView(R.id.historicRentalsRV)
    RecyclerView historicRentalsRV;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private RentalViewModel rentalViewModel;
    private RentalAdapter rentalAdapter;

    public RenterRentalsHistoric() {
        rentalAdapter = new RentalAdapter();
    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        BottomNavigationView bottomBar = getActivity().findViewById(R.id.bottom_navigation);
        bottomBar.setVisibility(View.GONE);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        BottomNavigationView bottomBar = getActivity().findViewById(R.id.bottom_navigation);
        bottomBar.setVisibility(View.VISIBLE);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historic_rentals, container, false);
        ButterKnife.bind(this, view);
        rentalViewModel = new RentalViewModel();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        rentalViewModel.getRentalsRenterHistoric().observe(this, rentals -> {
            if (rentals.isErrorDetected()) {
                Toast.makeText(getContext(), rentals.getErrorCode(), Toast.LENGTH_LONG).show();
            }

            else {
                rentalAdapter.setRentals(rentals.getObject());
                historicRentalsRV.setAdapter(rentalAdapter);
                historicRentalsRV.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });
        return view;
    }
}
