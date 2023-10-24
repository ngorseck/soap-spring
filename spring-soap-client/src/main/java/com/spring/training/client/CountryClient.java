package com.spring.training.client;

import com.spring.training.config.ClientConfig;
import com.spring.training.model.*;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;

import java.util.List;

import static com.spring.training.client.SoapActions.*;

@Component
public class CountryClient extends AbstractWsClient {

    public CountryClient(ClientConfig clientConfig, ClientInterceptor[] interceptors) {
        super(clientConfig, interceptors);
    }

    public List<Country> getCountries() {
        GetCountriesResponse response = sendRequest(new GetCountriesRequest(), GET_COUNTRIES, GetCountriesResponse.class);
        return response.getCountries();
    }

    public Country getCountry(String name) {
        GetCountryRequest request = new GetCountryRequest();
        request.setName(name);
        GetCountryResponse response = sendRequest(request, GET_COUNTRY, GetCountryResponse.class);
        return response.getCountry();
    }

    public Country createCountry(Country country) {
        CreateCountryRequest request = new CreateCountryRequest();
        request.setCountry(country);
        CreateCountryResponse response = sendRequest(request, CREATE_COUNTRY, CreateCountryResponse.class);
        return response.getCountry();
    }

    public Country updateCountry(String name, Country country) {
        country.setName(name);
        UpdateCountryRequest request = new UpdateCountryRequest();
        UpdateCountryResponse response = sendRequest(request, UPDATE_COUNTRY, UpdateCountryResponse.class);
        return response.getCountry();
    }

    public void deleteCountry(String name) {
        DeleteCountryRequest request = new DeleteCountryRequest();
        Country country = new Country();
        country.setName(name);
        sendRequest(request, DELETE_COUNTRY);
    }

}