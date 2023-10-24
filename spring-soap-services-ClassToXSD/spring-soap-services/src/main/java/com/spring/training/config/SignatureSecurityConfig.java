package com.spring.training.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.support.KeyStoreFactoryBean;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.soap.security.xwss.callback.KeyStoreCallbackHandler;

import java.util.Map;

@Configuration
@Profile("signature")
public class SignatureSecurityConfig extends AbstractSecurityConfig {

    public SignatureSecurityConfig(ServerConfig serverConfig) {
        super(serverConfig);
    }

    @Bean
    @Override
    public EndpointInterceptor securityInterceptor() {
        XwsSecurityInterceptor interceptor = new XwsSecurityInterceptor();
        Map<String, String> certificate = serverConfig.getCertificate();
        DefaultResourceLoader loader = new DefaultResourceLoader();
        interceptor.setPolicyConfiguration(loader.getResource(certificate.get("policy")));
        KeyStoreCallbackHandler handler = new KeyStoreCallbackHandler();
        handler.setDefaultAlias(certificate.get("alias"));
        KeyStoreFactoryBean keyStoreFactoryBean = keyStoreFactoryBean(serverConfig);
        handler.setTrustStore(keyStoreFactoryBean.getObject());
        handler.setKeyStore(keyStoreFactoryBean.getObject());
        handler.setPrivateKeyPassword(certificate.get("password"));
        interceptor.setCallbackHandler(handler);
        return interceptor;
    }

    @Bean
    public KeyStoreFactoryBean keyStoreFactoryBean(ServerConfig serverConfig) {
        Map<String, String> certificate = serverConfig.getCertificate();
        DefaultResourceLoader loader = new DefaultResourceLoader();
        KeyStoreFactoryBean keyStoreFactoryBean = new KeyStoreFactoryBean();
        keyStoreFactoryBean.setType(certificate.get("keyStoreType"));
        keyStoreFactoryBean.setLocation(loader.getResource(certificate.get("keyStore")));
        keyStoreFactoryBean.setPassword(certificate.get("password"));
        return keyStoreFactoryBean;
    }

}
