package com.example.smartcity.View.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.smartcity.R;
import com.example.smartcity.Utilitaries.Preferences;
import com.example.smartcity.WelcomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment {


    @BindView(R.id.profileInfos)
    CardView profileInfos;
    @BindView(R.id.rentalsOwner)
    CardView rentalsOwner;
    @BindView(R.id.rentalsRenter)
    CardView rentalsRenter;
    @BindView(R.id.addItem)
    CardView addItem;
    @BindView(R.id.logout)
    CardView logout;

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
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
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
            transaction.replace(R.id.fragment_container, new MyRentalsFragment());
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

    private View.OnClickListener logoutClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Preferences.signOutToken(getContext());
            startActivity(new Intent(getContext(), WelcomeActivity.class));
        }
    };


}
