package com.example.smartcity.View.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
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
import com.example.smartcity.Utilitaries.Preferences;
import com.example.smartcity.Utilitaries.StatusCode;
import com.example.smartcity.View.DisplayToast;
import com.google.android.material.textfield.TextInputLayout;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class UpdateProfileFragment extends Fragment {

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
    @BindView(R.id.spinnerLocalities)
    SearchableSpinner spinnerLocalities;
    @BindView(R.id.box)
    TextInputLayout box;
    @BindView(R.id.indeterminateBar)
    ProgressBar progressBar;
    @BindView(R.id.updateProfileView)
    ScrollView updateProfileView;

    @BindView(R.id.registerButton)
    Button register;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    Integer nbErrors;
    List<Locality> localityList;

    int position;
    int localityId;

    PersonViewModel personViewModel = new PersonViewModel();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_updateprofile, container, false);
        ButterKnife.bind(this, view);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        toolbar.setTitle(R.string.myInformations);
        LocalityViewModel localityViewModel = new LocalityViewModel(getContext());

        localityViewModel.getLocalities().observe(getViewLifecycleOwner(), localities -> {
            if (localities.isErrorDetected()) {
                DisplayToast.display(localities.getErrorCode());
            }

            else {
                localityList = localities.getObject();
                spinnerLocalities.setAdapter(new ArrayAdapter<Locality>(getContext(), android.R.layout.simple_spinner_dropdown_item, localityList));

                if(savedInstanceState == null) {
                    int personId = Preferences.getUserId();
                    personViewModel.getPerson(personId).observe(getViewLifecycleOwner(), person -> {
                        if (person.isErrorDetected()) {

                        }

                        else {
                            firstName.getEditText().setText(person.getObject().getFirstName());
                            lastName.getEditText().setText(person.getObject().getLastName());
                            mail.getEditText().setText(person.getObject().getEmail());
                            phone.getEditText().setText(person.getObject().getPhoneNumber());
                            street.getEditText().setText(person.getObject().getStreet());
                            box.getEditText().setText(person.getObject().getBox());

                            localityId = person.getObject().getLocality();

                            int size = localityList.size();
                            position = 0;
                            for(int i = 0; i < size; i++) {
                                if(localityId == localityList.get(i).getLocalityId()) {
                                    position = i;
                                    i = size - 1;
                                }
                            }
                            spinnerLocalities.setSelection(position);

                        }
                    });
                }
                spinnerLocalities.setSelection(position);
                updateProfileView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);



            }
        });

        register.setText(R.string.update);

        /*
        if(savedInstanceState != null) {
            spinnerLocalities.onSearchableItemClicked(spinnerLocalities.getItemAtPosition(savedInstanceState.getInt("spinnerLocalities")), savedInstanceState.getInt("spinnerLocalities"));
            // TODO : Set selection of the locality doesn't work !! Important
        }
         */

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
            PersonViewModel personViewModel = new PersonViewModel();
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
                personViewModel.updatePerson(person).observe(getViewLifecycleOwner(), personUpdate -> {
                    if (personUpdate.isErrorDetected()) {
                        if(personUpdate.getErrorCode() == StatusCode.CONFLICT.getErrorCode()) {
                            mail.setError(getString(R.string.emailAlreadyExists));
                            nbErrors++;
                        }

                        else {
                            Log.i("Alexis2", personUpdate.getErrorCode() + "");
                            DisplayToast.display(personUpdate.getErrorCode());
                        }


                    }

                    else {
                        DisplayToast.displaySpecific(R.string.updatePersonOk);
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container, new ProfileFragment());
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
        outState.putString("box",box.getEditText().getText().toString());
        //outState.putInt("position", position);
        outState.putInt("position", spinnerLocalities.getSelectedItemPosition());
        //outState.putParcelableArrayList("localities", (ArrayList<? extends Parcelable>) localityList);
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
            box.getEditText().setText(savedInstanceState.getString("box"));

            position = savedInstanceState.getInt("position");
        }
    }


}