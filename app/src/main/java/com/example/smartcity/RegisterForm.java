package com.example.smartcity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;

public class RegisterForm extends AppCompatActivity {

    @BindView(R.id.myAcountText)
    TextView myAccountText;

    @BindView(R.id.mailInput)
    EditText mailInput;

    @BindView(R.id.passwordInput)
    EditText passwordInput;

    @BindView(R.id.nameText)
    TextView nameText;

    @BindView(R.id.firstNameInput)
    EditText firstNameInput;

    @BindView(R.id.lastNameInput)
    EditText lastNameInput;

    @BindView(R.id.contactInfoInput)
    TextView contactInfoInput;

    @BindView(R.id.streetInput)
    EditText streetInput;

    @BindView(R.id.cityInput)
    EditText cityInput;

    @BindView(R.id.postalCodeInput)
    EditText postalCodeInput;

    @BindView(R.id.phoneInput)
    EditText phoneInput;

    @BindView(R.id.registerButton)
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);

        // initialisation
        //myAccountText = findViewById(R.id.myAcountText);
        //myAccountText.setText(R.string.myAccountText);
        mailInput.setHint(R.string.mailInput);
        passwordInput.setHint(R.string.passwordInput);
        nameText.setText(R.string.nameText);
        firstNameInput.setHint(R.string.firstNameInput);
        lastNameInput.setHint(R.string.lastNameInput);
        contactInfoInput.setText(R.string.contactInformationText);
        streetInput.setHint(R.string.streetInput);
        cityInput.setHint(R.string.cityInput);
        postalCodeInput.setHint(R.string.postalCodeInput);
        phoneInput.setHint(R.string.phoneInput);
        registerButton.setText(R.string.register);
    }
}
