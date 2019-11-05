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


    private EditText passwordInput;
    private TextView nameText;
    private EditText firstNameInput;
    private EditText lastNameInput;
    private TextView contactInfoInput;
    private EditText streetInput;
    private EditText cityInput;
    private EditText postalCodeInput;
    private EditText phoneInput;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);

        // initialisation
        myAccountText = findViewById(R.id.myAcountText);
        myAccountText.setText(R.string.myAccountText);
        mailInput = findViewById(R.id.mailInput);
        mailInput.setHint(R.string.mailInput);
        passwordInput = findViewById(R.id.passwordInput);
        passwordInput.setHint(R.string.passwordInput);
        nameText = findViewById(R.id.nameText);
        nameText.setText(R.string.nameText);
        firstNameInput = findViewById(R.id.firstNameInput);
        firstNameInput.setHint(R.string.firstNameInput);
        lastNameInput = findViewById(R.id.lastNameInput);
        lastNameInput.setHint(R.string.lastNameInput);
        contactInfoInput = findViewById(R.id.contactInfoInput);
        contactInfoInput.setText(R.string.contactInformationText);
        streetInput = findViewById(R.id.streetInput);
        streetInput.setHint(R.string.streetInput);
        cityInput = findViewById(R.id.cityInput);
        cityInput.setHint(R.string.cityInput);
        postalCodeInput = findViewById(R.id.postalCodeInput);
        postalCodeInput.setHint(R.string.postalCodeInput);
        phoneInput = findViewById(R.id.phoneInput);
        phoneInput.setHint(R.string.phoneInput);
        registerButton = findViewById(R.id.registerButton);
        registerButton.setText(R.string.register);




    }
}
