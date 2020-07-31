package com.example.smartcity.View.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.smartcity.DataAccess.InternetChecking;
import com.example.smartcity.Model.ApiResponse;
import com.example.smartcity.R;
import com.example.smartcity.Utilitaries.StatusCode;
import com.example.smartcity.View.DisplayToast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeFragment extends Fragment {

    @BindView(R.id.connection)
    Button connection;
    @BindView(R.id.regisration)
    Button registration;

    InternetChecking internetChecking = new InternetChecking();

    public WelcomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        ButterKnife.bind(this, view);
        connection.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new LogInFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        registration.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!internetChecking.isNetworkAvailable()) {
                    DisplayToast.display(StatusCode.NETWORKFAIL.getErrorCode());
                }

                else {
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, new RegisterFragment());
                    transaction.addToBackStack(null);
                    transaction.commit();
                }

            }
        });
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
    }

}
