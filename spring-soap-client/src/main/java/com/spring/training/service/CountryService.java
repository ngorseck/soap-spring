package com.spring.training.service;

import com.spring.training.client.CountryClient;
import com.spring.training.model.Country;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CountryService {

    final CountryClient client;

    public List<Country> getCountries() {
        return client.getCountries();
    }

    public Country getCountry(String name) {
        return client.getCountry(name);
    }

    public Country createCountry(Country country) {
        return client.createCountry(country);
    }

    public Country updateCountry(String name, Country country) {
        return client.updateCountry(name, country);
    }

    public void deleteCountry(String name) {
        client.deleteCountry(name);
    }

}