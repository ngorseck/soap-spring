package com.spring.training.client;

import com.spring.training.config.ClientConfig;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.SoapMessage;

public abstract class AbstractWsClient extends WebServiceGatewaySupport {

    public AbstractWsClient(ClientConfig clientConfig, ClientInterceptor[] interceptors) {
        setDefaultUri(clientConfig.getLocation());
        setInterceptors(interceptors);
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.spring.training.model");
        setMarshaller(marshaller);
        setUnmarshaller(marshaller);
    }

    public <T> void sendRequest(T request, String action) {
        getWebServiceTemplate().marshalSendAndReceive(request, message -> ((SoapMessage) message).setSoapAction(action));
    }

    public <T, U> U sendRequest(T request, String action, Class<U> clazz) {
        return clazz.cast(getWebServiceTemplate().marshalSendAndReceive(request, message -> ((SoapMessage) message).setSoapAction(action)));
    }

}
