package com.spring.training.service;

import com.spring.training.exception.SoapException;
import com.spring.training.mapping.PersonMapper;
import com.spring.training.repository.PersonRepository;
import com.spring.training.model.Person;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class PersonService {

    final PersonRepository personRepository;
    final PersonMapper personMapper;

    @Transactional(readOnly = true)
    public List<Person> getPersons() {
        return StreamSupport.stream(personRepository.findAll().spliterator(), false)
                .map(personMapper::toPerson)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Person getPerson(Long id) {
        return personMapper.toPerson(personRepository.findById(id).orElseThrow(() ->
                new SoapException("person not found with id : " + id)));
    }

    @Transactional
    public Person createPerson(Person person) {
        //person.setId(null);
        return personMapper.toPerson(personRepository.save(personMapper.fromPerson(person)));
    }

    @Transactional
    public Person updatePerson(Person person) {
        Long id = person.getId();
        return personRepository.findById(id)
                .map(entity -> {
                    person.setId(id);
                    return personMapper.toPerson(personRepository.save(personMapper.fromPerson(person)));
                }).orElseThrow(() -> new SoapException("person not found with id : " + id));
    }

    @Transactional
    public void deletePerson(Long id) {
        try {
            personRepository.deleteById(id);
        } catch (Exception e) {
            throw new SoapException("cannot delete person with id : " + id);
        }
    }

}
