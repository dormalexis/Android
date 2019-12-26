package com.example.smartcity.DataAccess.ViewModel;

import android.content.Context;

import com.example.smartcity.DataAccess.Repository.PersonDataAccess;
import com.example.smartcity.DataAccess.Repository.PersonRepository;
import com.example.smartcity.Model.Person;

public class PersonViewModel {
    private PersonDataAccess personDataAccess;

    public PersonViewModel(Context context)
    {
        personDataAccess = new PersonRepository(context);
    }

    public void postPerson(Person person)
    {
        personDataAccess.postPerson(person);
    }
}
