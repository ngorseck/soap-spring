package com.spring.training.service;

import com.spring.training.client.PersonClient;
import com.spring.training.model.Person;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {

    final PersonClient client;

    public List<Person> getPersons() {
        return client.getPersons();
    }

    public Person getPerson(Long id) {
        return client.getPerson(id);
    }

    public Person createPerson(Person person) {
        return client.createPerson(person);
    }

    public Person updatePerson(Long id, Person person) {
        return client.updatePerson(id, person);
    }

    public void deletePerson(Long id) {
        client.deletePerson(id);
    }

}