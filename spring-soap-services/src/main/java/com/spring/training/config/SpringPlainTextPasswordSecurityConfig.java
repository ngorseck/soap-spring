package com.spring.training.config;

import com.spring.training.service.UserAuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.soap.security.xwss.callback.SpringPlainTextPasswordValidationCallbackHandler;

import java.util.Map;

@Configuration
@Profile("springPassword")
public class SpringPlainTextPasswordSecurityConfig extends AbstractSecurityConfig {

    final UserAuthenticationService authenticationService;

    public SpringPlainTextPasswordSecurityConfig(ServerConfig serverConfig, UserAuthenticationService authenticationService) {
        super(serverConfig);
        this.authenticationService = authenticationService;
    }

    @Bean
    @Override
    public EndpointInterceptor securityInterceptor() {
        XwsSecurityInterceptor interceptor = new XwsSecurityInterceptor();
        Map<String, String> password = serverConfig.getPassword();
        DefaultResourceLoader loader = new DefaultResourceLoader();
        interceptor.setPolicyConfiguration(loader.getResource(password.get("policy")));
        SpringPlainTextPasswordValidationCallbackHandler handler = new SpringPlainTextPasswordValidationCallbackHandler();
        handler.setAuthenticationManager(authenticationManager());
        interceptor.setCallbackHandler(handler);
        return interceptor;
    }

    @Bean
    public ProviderManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(authenticationService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return new ProviderManager(provider);
    }

}