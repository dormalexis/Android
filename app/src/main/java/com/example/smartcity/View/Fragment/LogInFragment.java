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
import com.example.smartcity.DataAccess.ViewModel.ConnectionViewModel;
import com.example.smartcity.MainActivity;
import com.example.smartcity.Model.LoginModel;
import com.example.smartcity.R;
import com.google.android.material.textfield.TextInputLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LogInFragment extends Fragment {

    @BindView(R.id.loginFormTitle)
    TextView welcomeText;

    @BindView(R.id.mailInput)
    TextInputLayout mailInput;

    @BindView(R.id.passwordInput)
    TextInputLayout passwordInput;

    @BindView(R.id.logInButton)
    Button logingButton;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login,container,false);
        ButterKnife.bind(this, v);
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
            loginModel.setEmail(mailInput.getEditText().getText().toString());
            loginModel.setPassword(passwordInput.getEditText().getText().toString());

            connectionViewModel.getToken(loginModel,getContext()).observe(getViewLifecycleOwner(),token->{
                if(token.isErrorDetected())
                {
                    Toast.makeText(getContext(),R.string.loginFail,Toast.LENGTH_LONG).show();
                }
                else
                {
                    startActivity(new Intent(getContext(), MainActivity.class));
                }
            });

        }
    };


}
