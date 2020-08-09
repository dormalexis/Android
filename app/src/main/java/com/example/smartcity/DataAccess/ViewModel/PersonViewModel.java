package com.example.smartcity.DataAccess.ViewModel;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.example.smartcity.DataAccess.Repository.PersonDataAccess;
import com.example.smartcity.DataAccess.Repository.PersonRepository;
import com.example.smartcity.Model.ApiResponse;
import com.example.smartcity.Model.Person;

import static com.example.smartcity.Utilitaries.App.getContext;

public class PersonViewModel {
    private PersonDataAccess personDataAccess;

    public PersonViewModel()
    {
        personDataAccess = new PersonRepository(getContext());
    }

    public LiveData<ApiResponse> postPerson(Person person)
    {
        return personDataAccess.postPerson(person);
    }

    public LiveData<ApiResponse<Person>> getPerson(Integer personId)
    {
        return personDataAccess.getPerson(personId);
    }

    public LiveData<ApiResponse> updatePerson(Person person)
    {
        return personDataAccess.updatePerson(person);
    }
}
