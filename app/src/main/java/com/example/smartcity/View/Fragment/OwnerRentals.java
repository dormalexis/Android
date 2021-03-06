package com.example.smartcity.View.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.DataAccess.ViewModel.RentalViewModel;
import com.example.smartcity.Model.RentalDTO;
import com.example.smartcity.R;
import com.example.smartcity.View.DisplayToast;
import com.example.smartcity.View.RecyclerView.LayoutManagerRecyclerView;
import com.example.smartcity.View.RecyclerView.RentalAdapter;
import com.example.smartcity.View.RecyclerView.RentalOwnerValidationAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OwnerRentals extends Fragment {

    @BindView(R.id.inProgressRV)
    RecyclerView inProgressRV;

    @BindView(R.id.plannedRV)
    RecyclerView plannedRV;

    @BindView(R.id.waitingForPaymenRV)
    RecyclerView waitingForPaymentRV;

    @BindView(R.id.waitingForValidationRV)
    RecyclerView waitingForValidationRV;


    @BindView(R.id.noRentalsWaitingForValidation)
    MaterialCardView noRentalsWaitingForValidation;

    @BindView(R.id.noRentalsInProgress)
    MaterialCardView noRentalsInProgress;

    @BindView(R.id.noRentalsPaid)
    MaterialCardView noRentalsPaid;

    @BindView(R.id.noRentalsPlanned)
    MaterialCardView noRentalsPlanned;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.indeterminateBar)
    ProgressBar progressBar;

    @BindView(R.id.rentalsScrollView)
    ScrollView rentalsScrollView;


    List<RentalDTO> inProgressList;
    List<RentalDTO> plannedList;
    List<RentalDTO> waitingForPaymentList;
    List<RentalDTO> waitingForValidationList;

    List<RentalDTO> rentals;

    private RentalViewModel rentalViewModel;

    private RentalAdapter inProgress;
    private RentalAdapter planned;
    private RentalAdapter waitingForPayment;
    private RentalOwnerValidationAdapter waitingForValidation;


    public OwnerRentals() {


    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        this.inProgress = new RentalAdapter();
        this.planned = new RentalAdapter();
        this.waitingForPayment = new RentalAdapter();
        this.waitingForValidation = new RentalOwnerValidationAdapter();
        inProgressList = new ArrayList<>();
        plannedList = new ArrayList<>();
        waitingForPaymentList = new ArrayList<>();
        waitingForValidationList = new ArrayList<>();
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
        View view = inflater.inflate(R.layout.fragment_owner_rentals, container, false);
        ButterKnife.bind(this, view);
        rentalViewModel = new RentalViewModel();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        rentalViewModel.getRentalsOwnerInProgress().observe(this, rentals -> {
            if (rentals.isErrorDetected()) {
               DisplayToast.display(rentals.getErrorCode());
            } else {
                for (RentalDTO rental : rentals.getObject()) {
                    if (rental.isValid() != null && rental.isValid() && rental.isPaid()) {
                        if (rental.getDateFrom().compareTo(new Date()) <= 0) {
                            inProgressList.add(rental);
                        } else {
                            plannedList.add(rental);
                        }
                    } else {
                        if (!rental.isPaid() && rental.isValid() != null && rental.isValid()) {
                            waitingForPaymentList.add(rental);
                        }
                        if (rental.isValid() == null) {
                            waitingForValidationList.add(rental);
                        }
                    }
                }
                progressBar.setVisibility(View.GONE);
                rentalsScrollView.setVisibility(View.VISIBLE);

                if (waitingForPaymentList.isEmpty()) {
                    waitingForPaymentRV.setVisibility(View.GONE);
                    noRentalsPaid.setVisibility(View.VISIBLE);

                } else {
                    waitingForPayment.setRentals(waitingForPaymentList);
                    waitingForPaymentRV.setAdapter(waitingForPayment);
                    waitingForPaymentRV.setLayoutManager(new LayoutManagerRecyclerView());
                }


                if (plannedList.isEmpty()) {
                    plannedRV.setVisibility(View.GONE);
                    noRentalsPlanned.setVisibility(View.VISIBLE);
                } else {
                    planned.setRentals(plannedList);
                    plannedRV.setAdapter(planned);
                    plannedRV.setLayoutManager(new LayoutManagerRecyclerView());
                }

                if (waitingForValidationList.isEmpty()) {
                    waitingForValidationRV.setVisibility(View.GONE);
                    noRentalsWaitingForValidation.setVisibility(View.VISIBLE);
                } else {
                    waitingForValidation.setRentals(waitingForValidationList);
                    waitingForValidationRV.setAdapter(waitingForValidation);
                    waitingForValidationRV.setLayoutManager(new LayoutManagerRecyclerView());

                }

                if (inProgressList.isEmpty()) {
                    inProgressRV.setVisibility(View.GONE);
                    noRentalsInProgress.setVisibility(View.VISIBLE);
                } else {
                    inProgress.setRentals(inProgressList);
                    inProgressRV.setAdapter(inProgress);
                    inProgressRV.setLayoutManager(new LayoutManagerRecyclerView());
                }


            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
