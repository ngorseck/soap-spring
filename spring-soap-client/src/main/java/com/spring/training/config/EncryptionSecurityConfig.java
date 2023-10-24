package com.spring.training.config;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.callback.KeyStoreCallbackHandler;
import org.springframework.ws.soap.security.wss4j2.support.CryptoFactoryBean;

import java.util.Map;

@Configuration
@Profile("encryption")
@AllArgsConstructor
public class EncryptionSecurityConfig {

    final ClientConfig clientConfig;

    @Bean
    @SneakyThrows
    public Wss4jSecurityInterceptor securityInterceptor() {
        Wss4jSecurityInterceptor interceptor = new Wss4jSecurityInterceptor();
        Map<String, String> certificate = clientConfig.getCertificate();
        interceptor.setSecurementActions("Signature Encrypt");
        interceptor.setValidationActions("Signature Encrypt");
        interceptor.setSecurementUsername(certificate.get("alias"));
        interceptor.setSecurementPassword(certificate.get("password"));
        interceptor.setSecurementSignatureKeyIdentifier("DirectReference");
        interceptor.setSecurementEncryptionUser(certificate.get("alias"));
        CryptoFactoryBean cryptoFactoryBean = cryptoFactoryBean();
        interceptor.setSecurementSignatureCrypto(cryptoFactoryBean.getObject());
        interceptor.setValidationSignatureCrypto(cryptoFactoryBean.getObject());
        interceptor.setSecurementEncryptionCrypto(cryptoFactoryBean.getObject());
        interceptor.setValidationDecryptionCrypto(cryptoFactoryBean.getObject());
        KeyStoreCallbackHandler keyStoreCallbackHandler = new KeyStoreCallbackHandler();
        keyStoreCallbackHandler.setPrivateKeyPassword(certificate.get("password"));
        interceptor.setValidationCallbackHandler(keyStoreCallbackHandler);
        return interceptor;
    }

    @Bean
    @SneakyThrows
    public CryptoFactoryBean cryptoFactoryBean() {
        CryptoFactoryBean cryptoFactoryBean = new CryptoFactoryBean();
        Map<String, String> certificate = clientConfig.getCertificate();
        DefaultResourceLoader loader = new DefaultResourceLoader();
        cryptoFactoryBean.setKeyStorePassword(certificate.get("password"));
        cryptoFactoryBean.setKeyStoreLocation(loader.getResource(certificate.get("keyStore")));
        cryptoFactoryBean.setDefaultX509Alias(certificate.get("alias"));
        return cryptoFactoryBean;
    }

}
