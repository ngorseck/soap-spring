package com.spring.training.service;

import com.spring.training.exception.SoapException;
import com.spring.training.mapping.CountryMapper;
import com.spring.training.repository.CountryRepository;
import com.spring.training.model.Country;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class CountryService {

    final CountryRepository repository;
    final CountryMapper mapper;

    @Transactional(readOnly = true)
    public List<Country> getCountries() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(mapper::toCountry)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Country getCountry(String name) {
        return mapper.toCountry(repository.findByNameIgnoreCase(name).orElseThrow(() ->
                new SoapException("country not found with name : " + name)));
    }

    @Transactional
    public Country createCountry(Country country) {
        repository.findByNameIgnoreCase(country.getName())
                .ifPresent(entity -> {
                    throw new SoapException("duplicated country with name : " + country.getName());
                });
        return mapper.toCountry(repository.save(mapper.fromCountry(country)));
    }

    @Transactional
    public Country updateCountry(Country country) {
        String name = country.getName();
        return repository.findByNameIgnoreCase(name)
                .map(entity -> {
                    country.setName(name);
                    return mapper.toCountry(repository.save(mapper.fromCountry(country)));
                }).orElseThrow(() -> new SoapException("country not found with name : " + name));
    }

    @Transactional
    public void deleteCountry(String name) {
        try {
            repository.deleteById(name);
        } catch (Exception e) {
            throw new SoapException("cannot delete country with name : " + name);
        }
    }

}