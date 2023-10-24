package com.spring.training.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.ws.soap.security.support.KeyStoreFactoryBean;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.soap.security.xwss.callback.KeyStoreCallbackHandler;

import java.util.Map;

@Configuration
@Profile("signature")
@AllArgsConstructor
public class SignatureSecurityConfig {

    final ClientConfig clientConfig;

    @Bean
    public XwsSecurityInterceptor securityInterceptor() {
        XwsSecurityInterceptor interceptor = new XwsSecurityInterceptor();
        Map<String, String> certificate = clientConfig.getCertificate();
        DefaultResourceLoader loader = new DefaultResourceLoader();
        interceptor.setPolicyConfiguration(loader.getResource(certificate.get("policy")));
        KeyStoreCallbackHandler handler = new KeyStoreCallbackHandler();
        handler.setDefaultAlias(certificate.get("alias"));
        KeyStoreFactoryBean keyStoreFactoryBean = keyStoreFactoryBean();
        handler.setTrustStore(keyStoreFactoryBean.getObject());
        handler.setKeyStore(keyStoreFactoryBean.getObject());
        handler.setPrivateKeyPassword(certificate.get("password"));
        interceptor.setCallbackHandler(handler);
        return interceptor;
    }

    @Bean
    public KeyStoreFactoryBean keyStoreFactoryBean() {
        Map<String, String> certificate = clientConfig.getCertificate();
        DefaultResourceLoader loader = new DefaultResourceLoader();
        KeyStoreFactoryBean keyStoreFactoryBean = new KeyStoreFactoryBean();
        keyStoreFactoryBean.setLocation(loader.getResource(certificate.get("keyStore")));
        keyStoreFactoryBean.setPassword(certificate.get("password"));
        return keyStoreFactoryBean;
    }

}
