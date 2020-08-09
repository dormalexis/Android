package com.example.smartcity.View.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smartcity.DataAccess.ViewModel.ConnectionViewModel;
import com.example.smartcity.MainActivity;
import com.example.smartcity.Model.LoginModel;
import com.example.smartcity.R;
import com.example.smartcity.Utilitaries.StatusCode;
import com.example.smartcity.View.DisplayToast;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogInFragment extends Fragment {

    @BindView(R.id.mailInput)
    TextInputLayout mailInput;

    @BindView(R.id.passwordInput)
    TextInputLayout passwordInput;

    @BindView(R.id.logInButton)
    Button logingButton;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.indeterminateBar)
    ProgressBar progressBar;

    Integer nbErrors;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, v);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        logingButton.setOnClickListener(loginListener);
        return v;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private View.OnClickListener loginListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            LoginModel loginModel = new LoginModel();
            ConnectionViewModel connectionViewModel = new ConnectionViewModel(getContext());
            nbErrors = 0;

            try {
                loginModel.setEmail(mailInput.getEditText().getText().toString());
                mailInput.setError(null);

            } catch (Exception e) {
                nbErrors++;
                mailInput.setError(getString(R.string.emailException));
            }

            try {
                loginModel.setPassword(passwordInput.getEditText().getText().toString());
                passwordInput.setError(null);

            } catch (Exception e) {
                nbErrors++;
                passwordInput.setError(getString(R.string.passwordException));
            }

            if(nbErrors == 0) {
                progressBar.setVisibility(View.VISIBLE);
                connectionViewModel.getToken(loginModel).observe(getViewLifecycleOwner(), token -> {
                    progressBar.setVisibility(View.GONE);
                    if (token.isErrorDetected())
                    {
                        if(token.getErrorCode() == StatusCode.NETWORKFAIL.getErrorCode()) {
                            DisplayToast.displaySpecific(R.string.NETWORKFAIL);
                            nbErrors++;
                        }

                        else if(token.getErrorCode() == StatusCode.UNAUTHORIZED.getErrorCode()) {
                            DisplayToast.displaySpecific(R.string.inccorectPassword);
                            nbErrors++;
                        }

                        else {
                            DisplayToast.displaySpecific(R.string.noAccountWithThisEmail);
                        }

                    }

                    else {
                        startActivity(new Intent(getContext(), MainActivity.class));
                    }
                });
            }



        }
    };


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("mail", mailInput.getEditText().getText().toString());
        outState.putString("password", passwordInput.getEditText().getText().toString());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState != null) {
            mailInput.getEditText().setText(savedInstanceState.getString("mail"));
            passwordInput.getEditText().setText(savedInstanceState.getString("password"));
        }

    }


}
