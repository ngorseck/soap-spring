package com.spring.training.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.soap.security.xwss.callback.SimplePasswordValidationCallbackHandler;

import java.util.Map;

@Configuration
@Profile("password")
public class SimplePasswordSecurityConfig extends AbstractSecurityConfig {

    public SimplePasswordSecurityConfig(ServerConfig serverConfig) {
        super(serverConfig);
    }

    @Bean
    @Override
    public EndpointInterceptor securityInterceptor() {
        XwsSecurityInterceptor interceptor = new XwsSecurityInterceptor();
        Map<String, String> password = serverConfig.getPassword();
        DefaultResourceLoader loader = new DefaultResourceLoader();
        interceptor.setPolicyConfiguration(loader.getResource(password.get("policy")));
        SimplePasswordValidationCallbackHandler handler = new SimplePasswordValidationCallbackHandler();
        handler.setUsersMap(serverConfig.getUsers());
        interceptor.setCallbackHandler(handler);
        return interceptor;
    }

}
