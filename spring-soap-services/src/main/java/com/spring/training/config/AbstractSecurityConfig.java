package com.spring.training.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.server.endpoint.interceptor.PayloadLoggingInterceptor;

import java.util.List;

@AllArgsConstructor
public abstract class AbstractSecurityConfig extends WsConfigurerAdapter {

    final ServerConfig serverConfig;

    public abstract EndpointInterceptor securityInterceptor();

    @Bean
    public PayloadLoggingInterceptor payloadLoggingInterceptor() {
        return new PayloadLoggingInterceptor();
    }

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        interceptors.add(payloadLoggingInterceptor());
        interceptors.add(securityInterceptor());
    }

}
