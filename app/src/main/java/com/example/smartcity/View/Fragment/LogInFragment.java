package com.example.smartcity.View.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.smartcity.DataAccess.ViewModel.ConnectionViewModel;
import com.example.smartcity.Model.LoginModel;
import com.example.smartcity.R;
import com.example.smartcity.View.Fragment.CheckFragment;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login,container,false);
        ButterKnife.bind(this, v);
        logingButton.setOnClickListener(loginListener);
        registerButton.setOnClickListener(registerListerner);
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
            ConnectionViewModel connectionViewModel = new ConnectionViewModel();
            loginModel.setEmail(mailInput.getText().toString());
            loginModel.setPassword(passwordInput.getText().toString());
            connectionViewModel.getToken(loginModel, getContext());
        }
    };

    private View.OnClickListener registerListerner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new RegisterFragment());
            transaction.addToBackStack(new LogInFragment().getClass().getName());
            transaction.commit();
        }
    };

}
