package com.example.smartcity.View.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.smartcity.DataAccess.ViewModel.LocalityViewModel;
import com.example.smartcity.DataAccess.ViewModel.PersonViewModel;
import com.example.smartcity.Model.Locality;
import com.example.smartcity.Model.Person;
import com.example.smartcity.R;
import com.google.android.material.textfield.TextInputLayout;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RegisterFragment extends Fragment {

    @BindView(R.id.firstNameInput)
    TextInputLayout firstName;
    @BindView(R.id.lastNameInput)
    TextInputLayout lastName;
    @BindView(R.id.mailInput)
    TextInputLayout mail;
    @BindView(R.id.phoneInput)
    TextInputLayout phone;
    @BindView(R.id.streetInput)
    TextInputLayout street;
    @BindView(R.id.passwordInput)
    TextInputLayout password;
    @BindView(R.id.registerButton)
    Button register;
    @BindView(R.id.spinnerLocalities)
    SearchableSpinner spinnerLocalities;
    @BindView(R.id.box)
    TextInputLayout box;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);

        LocalityViewModel localityViewModel = new LocalityViewModel(getContext());

        localityViewModel.getLocalities().observe(getViewLifecycleOwner(), localities -> {
            if (localities.isErrorDetected()) {
                Toast.makeText(getContext(), localities.getErrorCode(), Toast.LENGTH_LONG).show();
            } else {
                spinnerLocalities.setAdapter(new ArrayAdapter<Locality>(getContext(), android.R.layout.simple_spinner_dropdown_item, localities.getObject()));
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
            Person person = new Person(getContext());
            PersonViewModel personViewModel = new PersonViewModel(getContext());
            Integer nbErrors = 0;

            try {
                person.setFirstName(firstName.getEditText().getText().toString());
                firstName.setError(null);
            } catch (Exception e) {
                nbErrors++;
                firstName.setError(getString(R.string.firstNameException));
            }

            try {
                person.setLastName(lastName.getEditText().getText().toString());
                lastName.setError(null);
            } catch (Exception e) {
                nbErrors++;
                lastName.setError(getString(R.string.firstNameException));
            }

            try {
                person.setEmail(mail.getEditText().getText().toString());
                mail.setError(null);
            } catch (Exception e) {
                nbErrors++;
                mail.setError(getString(R.string.emailException));
            }

            try {
                person.setPhoneNumber(phone.getEditText().getText().toString());
                phone.setError(null);
            } catch (Exception e) {
                nbErrors++;
                phone.setError(getString(R.string.phoneNumberException));
            }

            try {
                person.setBox(box.getEditText().getText().toString());
                box.setError(null);
            } catch (Exception e) {
                nbErrors++;
                box.setError(getString(R.string.boxException));
            }

            try {
                person.setPassword(password.getEditText().getText().toString());
                password.setError(null);
            } catch (Exception e) {
                nbErrors++;
                password.setError(getString(R.string.passwordException));
            }

            try {
                person.setStreet(street.getEditText().getText().toString());
                street.setError(null);
            } catch (Exception e) {
                nbErrors++;
                street.setError(getString(R.string.streetNameException));
            }

            person.setLocality(((Locality) spinnerLocalities.getSelectedItem()).getLocalityId());


            if (nbErrors == 0) {
                personViewModel.postPerson(person).observe(getViewLifecycleOwner(), personPost -> {
                    if (personPost.isErrorDetected()) {
                        Toast.makeText(getContext(), personPost.getErrorCode(), Toast.LENGTH_LONG).show();
                    } else {
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container, new LogInFragment());
                        transaction.commit();
                    }
                });
            }
        }
    };


}