package com.spring.training.endpoint;

import com.spring.training.model.*;
import com.spring.training.service.PersonService;
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
public class PersonEndpoint {

    final PersonService service;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonsRequest")
    @ResponsePayload
    @SoapAction(GET_PERSONS)
    public GetPersonsResponse getPersons(@RequestPayload GetPersonsRequest request) {
        GetPersonsResponse response = new GetPersonsResponse();
        response.getPersons().addAll(service.getPersons());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonRequest")
    @ResponsePayload
    @SoapAction(GET_PERSON)
    public GetPersonResponse getPerson(@RequestPayload GetPersonRequest request) {
        GetPersonResponse response = new GetPersonResponse();
        response.setPerson(service.getPerson(request.getId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createPersonRequest")
    @ResponsePayload
    @SoapAction(CREATE_PERSON)
    public CreatePersonResponse createPerson(@RequestPayload CreatePersonRequest request) {
        CreatePersonResponse response = new CreatePersonResponse();
        response.setPerson(service.createPerson(request.getPerson()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updatePersonRequest")
    @ResponsePayload
    @SoapAction(UPDATE_PERSON)
    public UpdatePersonResponse updatePerson(@RequestPayload UpdatePersonRequest request) {
        UpdatePersonResponse response = new UpdatePersonResponse();
        response.setPerson(service.updatePerson(request.getPerson()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deletePersonRequest")
    @SoapAction(DELETE_PERSON)
    public void deletePerson(@RequestPayload DeletePersonRequest request) {
        service.deletePerson(request.getId());
    }

}