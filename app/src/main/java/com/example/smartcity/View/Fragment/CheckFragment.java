package com.example.smartcity.View.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.DataAccess.ViewModel.RentalViewModel;
import com.example.smartcity.R;
import com.example.smartcity.View.RecyclerView.RentalAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckFragment extends Fragment {

    @BindView(R.id.myValidationRV)
    RecyclerView myValidationRV;
    private RentalViewModel rentalViewModel;
    private RentalAdapter rentalAdapter;

    public CheckFragment() {}

    @Override
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        rentalAdapter = new RentalAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check,container,false);
        ButterKnife.bind(this, view);
        rentalViewModel = new RentalViewModel(getContext());
        rentalViewModel.getValidations().observe(this, rentals -> {
            if(rentals.isErrorDetected())
            {
                Toast.makeText(getContext(),rentals.getErrorCode().getMessage(),Toast.LENGTH_LONG);
            }
            else
            {
                rentalAdapter.setRentals(rentals.getObject());
                myValidationRV.setAdapter(rentalAdapter);
                myValidationRV.setLayoutManager(new LinearLayoutManager(getContext()));
            }

        });
        return view;
    }
}
