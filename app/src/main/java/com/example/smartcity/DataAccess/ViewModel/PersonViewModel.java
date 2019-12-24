package com.example.smartcity.DataAccess.ViewModel;

import com.example.smartcity.DataAccess.Repository.PersonDataAccess;
import com.example.smartcity.DataAccess.Repository.PersonRepository;
import com.example.smartcity.Model.Person;

public class PersonViewModel {
    private PersonDataAccess personDataAccess;

    public PersonViewModel()
    {
        personDataAccess = new PersonRepository();
    }

    public void postPerson(Person person)
    {
        personDataAccess.postPerson(person);
    }
}
