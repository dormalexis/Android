package com.example.smartcity.View.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.smartcity.DataAccess.ViewModel.ConnectionViewModel;
import com.example.smartcity.Model.LoginModel;
import com.example.smartcity.R;
import com.example.smartcity.Utilitaries.Preferences;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogInFragment extends Fragment {

    @BindView(R.id.welcomeText)
    TextView welcomeText;

    @BindView(R.id.mailInput)
    EditText mailInput;

    @BindView(R.id.passwordInput)
    EditText passwordInput;

    @BindView(R.id.logInButton)
    Button logingButton;

    @BindView(R.id.registerButton)
    Button registerButton;

    @BindView(R.id.signOutButton)
    Button signOutButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login,container,false);
        ButterKnife.bind(this, v);
        logingButton.setOnClickListener(loginListener);
        registerButton.setOnClickListener(registerListerner);
        signOutButton.setOnClickListener(signOutListener);
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
            loginModel.setEmail(mailInput.getText().toString());
            loginModel.setPassword(passwordInput.getText().toString());


            connectionViewModel.getToken(loginModel,getContext()).observe(getViewLifecycleOwner(),token->{
                if(token.isErrorDetected())
                {
                    Toast.makeText(getContext(),R.string.loginFail,Toast.LENGTH_LONG).show();
                }
                else
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setCancelable(true);
                    builder.setTitle(R.string.loginSucceed);
                    builder.setMessage(R.string.loginSucceed);
                    builder.setPositiveButton("Voir les annonces",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                    transaction.replace(R.id.fragment_container, new HomeFragment());
                                    transaction.addToBackStack(new MyItemsFragment().getClass().getName());
                                    transaction.commit();
                                }
                            });
                            builder.setNegativeButton("", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });

        }
    };

    private View.OnClickListener registerListerner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /*
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new RegisterFragment());
            transaction.addToBackStack(new LogInFragment().getClass().getName());
            transaction.commit();
             */
        }
    };

    private View.OnClickListener signOutListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Preferences.signOutToken(getContext());
            Toast.makeText(getContext(),R.string.signOutToast,Toast.LENGTH_LONG).show();
        }
    };

}
