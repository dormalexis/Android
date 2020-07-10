package com.example.smartcity.View.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.smartcity.MainActivity;
import com.example.smartcity.R;
import com.example.smartcity.Utilitaries.Preferences;
import com.example.smartcity.WelcomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment {

    @BindView(R.id.profileInfos)
    CardView profileInfos;
    @BindView(R.id.myReviews)
    CardView myReviews;
    @BindView(R.id.rentalsOwner)
    CardView rentalsOwner;
    @BindView(R.id.rentalsRenter)
    CardView rentalsRenter;
    @BindView(R.id.ownerFollowing)
    CardView ownerFollowing;
    @BindView(R.id.ownerHistoric)
    CardView ownerHistoric;
    @BindView(R.id.rentalsRenterHistoric)
    CardView rentalsRenterHistoric;





    @BindView(R.id.addItem)
    CardView addItem;
    @BindView(R.id.logoutButton)
    Button logout;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        profileInfos.setOnClickListener(onProfileClick);
        rentalsOwner.setOnClickListener(ownerClick);
        rentalsRenter.setOnClickListener(renterClick);
        addItem.setOnClickListener(addItemClick);
        logout.setOnClickListener(logoutClick);
        myReviews.setOnClickListener(myReviewsClick);
        ownerFollowing.setOnClickListener(ownerFollowingClick);
        ownerHistoric.setOnClickListener(ownerHistoricClick);
        rentalsRenterHistoric.setOnClickListener(rentalsRenterHistoricClick);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstance) {

        super.onCreate(savedInstance);
        setRetainInstance(true);
    }

    private View.OnClickListener onProfileClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new ProfileFragment());
            transaction.addToBackStack(new ProfileFragment().getClass().getName());
            transaction.commit();
        }
    };

    private View.OnClickListener ownerClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new MyItemsFragment());
            transaction.addToBackStack(new ProfileFragment().getClass().getName());
            transaction.commit();
        }
    };

    private View.OnClickListener renterClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new RenterRentals());
            transaction.addToBackStack(new ProfileFragment().getClass().getName());
            transaction.commit();
        }
    };

    private View.OnClickListener addItemClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new AddItemFragment());
            transaction.addToBackStack(new ProfileFragment().getClass().getName());
            transaction.commit();
        }
    };

    private View.OnClickListener myReviewsClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new MyReviews());
            transaction.addToBackStack(new ProfileFragment().getClass().getName());
            transaction.commit();
        }
    };

    private View.OnClickListener ownerFollowingClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new OwnerRentals());
            transaction.addToBackStack(new ProfileFragment().getClass().getName());
            transaction.commit();
        }
    };

    private View.OnClickListener ownerHistoricClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new OwnerRentalsHistoric());
            transaction.addToBackStack(new ProfileFragment().getClass().getName());
            transaction.commit();
        }
    };

    private View.OnClickListener rentalsRenterHistoricClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new RenterRentals());
            transaction.addToBackStack(new ProfileFragment().getClass().getName());
            transaction.commit();
        }
    };

    private View.OnClickListener logoutClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Preferences.signOutToken(getContext());
            startActivity(new Intent(getContext(), WelcomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK ));
        }
    };


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


}
