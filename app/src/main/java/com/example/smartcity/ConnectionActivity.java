package com.example.smartcity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ConnectionActivity extends AppCompatActivity {
    private TextView welcomeText;
    private EditText mailInput;
    private EditText passwordInput;
    private Button logingButton;
    private Button registerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        welcomeText = findViewById(R.id.welcomeText);
        welcomeText.setText(R.string.welcomeText);

        mailInput = findViewById(R.id.mailInput);
        mailInput.setHint(R.string.mailInput);
        passwordInput = findViewById(R.id.passwordInput);
        passwordInput.setHint(R.string.passwordInput);

        logingButton = findViewById(R.id.logInButton);
        logingButton.setText(R.string.login);
        logingButton.setOnClickListener(loginListener);
        registerButton = findViewById(R.id.registerButton);
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
