package com.spring.training.endpoint;

import com.spring.training.model.*;
import com.spring.training.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.server.endpoint.annotation.SoapAction;

import static com.spring.training.config.ApplicationConfig.NAMESPACE_URI;
import static com.spring.training.endpoint.SoapActions.*;

@Endpoint
@AllArgsConstructor
public class CountryEndpoint {

    final CountryService service;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountriesRequest")
    @ResponsePayload
    @SoapAction(GET_COUNTRIES)
    public GetCountriesResponse getCountry(@RequestPayload GetCountriesRequest request) {
        GetCountriesResponse response = new GetCountriesResponse();
        response.getCountries().addAll(service.getCountries());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    @SoapAction(GET_COUNTRY)
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(service.getCountry(request.getName()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createCountryRequest")
    @ResponsePayload
    @SoapAction(CREATE_COUNTRY)
    public CreateCountryResponse createCountry(@RequestPayload CreateCountryRequest request) {
        CreateCountryResponse response = new CreateCountryResponse();
        response.setCountry(service.createCountry(request.getCountry()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateCountryRequest")
    @ResponsePayload
    @SoapAction(UPDATE_COUNTRY)
    public UpdateCountryResponse updateCountry(@RequestPayload UpdateCountryRequest request) {
        UpdateCountryResponse response = new UpdateCountryResponse();
        response.setCountry(service.updateCountry(request.getCountry()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteCountryRequest")
    @SoapAction(DELETE_COUNTRY)
    public void deleteCountry(@RequestPayload DeleteCountryRequest request) {
        service.deleteCountry(request.getName());
    }

}