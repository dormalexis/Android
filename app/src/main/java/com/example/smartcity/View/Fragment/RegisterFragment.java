package com.example.smartcity.View.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.smartcity.DataAccess.ViewModel.LocalityViewModel;
import com.example.smartcity.DataAccess.ViewModel.PersonViewModel;

import com.example.smartcity.Model.ApiResponse;
import com.example.smartcity.Model.Locality;
import com.example.smartcity.Model.Person;
import com.example.smartcity.R;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RegisterFragment extends Fragment {

    @BindView(R.id.firstNameInput)
    EditText firstName;
    @BindView(R.id.lastNameInput)
    EditText lastName;
    @BindView(R.id.mailInput)
    EditText mail;
    @BindView(R.id.phoneInput)
    EditText phone;
    @BindView(R.id.streetInput)
    EditText street;
    @BindView(R.id.passwordInput)
    EditText password;
    @BindView(R.id.registerButton)
    Button register;
    @BindView(R.id.spinnerLocalities)
    SearchableSpinner spinnerLocalities;

    LocalityViewModel localityViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register,container,false);
        ButterKnife.bind(this, view);

        localityViewModel = new LocalityViewModel(getContext());

        localityViewModel.getLocalities().observe(getViewLifecycleOwner(),localities -> {
            if(localities.isErrorDetected())
            {
                Toast.makeText(getContext(),localities.getErrorCode().getMessage(),Toast.LENGTH_LONG);
            }
            else
            {
                spinnerLocalities.setAdapter(new ArrayAdapter<Locality>(getContext(), android.R.layout.simple_spinner_dropdown_item,localities.getObject()));
            }

        });

        register.setOnClickListener(registerListenr);
        spinnerLocalities.setTitle(getResources().getString(R.string.titleSpinnerLocalities));
        spinnerLocalities.setPositiveButton((getResources().getString(R.string.validate)));

        return view;
    }


    private View.OnClickListener registerListenr = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Person person = new Person();
            PersonViewModel personViewModel = new PersonViewModel(getContext());
            person.setFirstName(firstName.getText().toString());
            person.setLastName(lastName.getText().toString());
            person.setEmail(mail.getText().toString());
            person.setPhoneNumber(phone.getText().toString());
            person.setStreetNumber(10);
            person.setBox("10");
            person.setPassword(password.getText().toString());
            person.setStreet(street.getText().toString());
            person.setLocality(((Locality) spinnerLocalities.getSelectedItem()).getLocalityId());
            person.setAvailabilityDescription("Tous les jours");
            person.setRole(3);
            person.setBlocked(false);

            personViewModel.postPerson(person).observe(getViewLifecycleOwner(),personPost -> {
                if(personPost.isErrorDetected())
                {
                    Toast.makeText(getContext(),personPost.getErrorCode().getMessage(),Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getContext(),R.string.PostPersonOk,Toast.LENGTH_LONG).show();
                }
            });

        }
    };


}