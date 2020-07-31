package com.example.smartcity.View.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.smartcity.DataAccess.ViewModel.LocalityViewModel;
import com.example.smartcity.DataAccess.ViewModel.PersonViewModel;
import com.example.smartcity.Model.Locality;
import com.example.smartcity.Model.Person;
import com.example.smartcity.R;
import com.example.smartcity.Utilitaries.StatusCode;
import com.example.smartcity.View.DisplayToast;
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
    @BindView(R.id.spinnerLocalities)
    SearchableSpinner spinnerLocalities;
    @BindView(R.id.box)
    TextInputLayout box;

    @BindView(R.id.registerButton)
    Button register;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    Integer nbErrors;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        
        LocalityViewModel localityViewModel = new LocalityViewModel(getContext());

        localityViewModel.getLocalities().observe(getViewLifecycleOwner(), localities -> {
            if (localities.isErrorDetected()) {
                DisplayToast.display(localities.getErrorCode());
            }

            else {
                spinnerLocalities.setAdapter(new ArrayAdapter<Locality>(getContext(), android.R.layout.simple_spinner_dropdown_item, localities.getObject()));
            }
        });

        if(savedInstanceState != null) {
            spinnerLocalities.onSearchableItemClicked(spinnerLocalities.getItemAtPosition(savedInstanceState.getInt("spinnerLocalities")), savedInstanceState.getInt("spinnerLocalities"));
            // TODO : Set selection of the locality doesn't work !! Important
        }

        register.setOnClickListener(registerListenr);
        spinnerLocalities.setPrompt(getResources().getString(R.string.titleSpinnerLocalities));
        spinnerLocalities.setTitle(getResources().getString(R.string.titleSpinnerLocalities));
        spinnerLocalities.setPositiveButton((getResources().getString(R.string.validate)));

        return view;
    }


    private View.OnClickListener registerListenr = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Person person = new Person();
            PersonViewModel personViewModel = new PersonViewModel(getContext());
            nbErrors = 0;

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

            if(spinnerLocalities.getSelectedItem() != null) {
                person.setLocality(((Locality) spinnerLocalities.getSelectedItem()).getLocalityId());
            }

            if (nbErrors == 0) {
                personViewModel.postPerson(person).observe(getViewLifecycleOwner(), personPost -> {
                    if (personPost.isErrorDetected()) {
                        if(personPost.getErrorCode() == StatusCode.CONFLICT.getErrorCode()) {
                            mail.setError(getString(R.string.emailAlreadyExists));
                            nbErrors++;
                        }

                        else {
                            DisplayToast.display(personPost.getErrorCode());
                        }


                    } else {
                        DisplayToast.displaySpecific(R.string.postPersonOk);
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container, new LogInFragment());
                        transaction.commit();
                    }
                });
            }
        }
    };

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("firstName", firstName.getEditText().getText().toString());
        outState.putString("lastName",lastName.getEditText().getText().toString());
        outState.putString("mail",mail.getEditText().getText().toString());
        outState.putString("phone",phone.getEditText().getText().toString());
        outState.putString("street",street.getEditText().getText().toString());
        outState.putString("password",password.getEditText().getText().toString());
        outState.putString("box",box.getEditText().getText().toString());
        outState.putInt("spinnerLocalities", spinnerLocalities.getSelectedItemPosition());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState != null)
        {
            firstName.getEditText().setText(savedInstanceState.getString("firstName"));
            lastName.getEditText().setText(savedInstanceState.getString("lastName"));
            mail.getEditText().setText(savedInstanceState.getString("mail"));
            phone.getEditText().setText(savedInstanceState.getString("phone"));
            street.getEditText().setText(savedInstanceState.getString("street"));
            password.getEditText().setText(savedInstanceState.getString("password"));
            box.getEditText().setText(savedInstanceState.getString("box"));
            spinnerLocalities.setSelection(savedInstanceState.getInt("spinnerLocalities"));
        }
    }


}