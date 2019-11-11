package com.example.smartcity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;

public class ConnectionActivity extends AppCompatActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        welcomeText.setText(R.string.welcomeText);

        mailInput.setHint(R.string.mailInput);
        passwordInput = findViewById(R.id.passwordInput);
        passwordInput.setHint(R.string.passwordInput);

        logingButton.setText(R.string.login);
        logingButton.setOnClickListener(loginListener);

        registerButton.setText(R.string.register);
        registerButton.setOnClickListener(registerListerner);
    }


    // Listener
    private View.OnClickListener loginListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // actions à faire quand on clique sur le bouton login
        }
    };

    private View.OnClickListener registerListerner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ConnectionActivity.this,RegisterForm.class);
            startActivity(intent);
        }
    };

}
