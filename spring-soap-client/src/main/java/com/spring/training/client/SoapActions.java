package com.spring.training.client;

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

}
