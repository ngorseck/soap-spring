package com.spring.training.endpoint;

import java.util.Properties;

public interface SoapActions {

    String GET_PERSONS = "http://spring.com/training/getPersonsRequest";
    String GET_PERSON = "http://spring.com/training/getPersonRequest";
    String CREATE_PERSON = "http://spring.com/training/createPersonRequest";
    String UPDATE_PERSON = "http://spring.com/training/updatePersonRequest";
    String DELETE_PERSON = "http://spring.com/training/deletePersonRequest";

    String GET_COUNTRIES = "http://spring.com/training/getCountriesRequest";
    String GET_COUNTRY = "http://spring.com/training/getCountryRequest";
    String CREATE_COUNTRY = "http://spring.com/training/createCountryRequest";
    String UPDATE_COUNTRY = "http://spring.com/training/updateCountryRequest";
    String DELETE_COUNTRY = "http://spring.com/training/deleteCountryRequest";

    static Properties getPersonEndpointActions() {
        Properties actions = new Properties();
        actions.put("getPersons", GET_PERSONS);
        actions.put("getPerson", GET_PERSON);
        actions.put("createPerson", CREATE_PERSON);
        actions.put("updatePerson", UPDATE_PERSON);
        actions.put("deletePerson", DELETE_PERSON);
        return actions;
    }

    static Properties getCountryEndpointActions() {
        Properties actions = new Properties();
        actions.put("getCountries", GET_COUNTRIES);
        actions.put("getCountry", GET_COUNTRY);
        actions.put("createCountry", CREATE_COUNTRY);
        actions.put("updateCountry", UPDATE_COUNTRY);
        actions.put("deleteCountry", DELETE_COUNTRY);
        return actions;
    }

}
