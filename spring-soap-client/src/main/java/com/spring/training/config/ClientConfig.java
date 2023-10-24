package com.spring.training.config;

import lombok.Data;

import java.util.Map;

@Data
public class ClientConfig {

    String location;

    Map<String, Object> security;

    public Map<String, String> getCertificate() {
        return (Map<String, String>) security.get("certificate");
    }

    public Map<String, String> getPassword() {
        return (Map<String, String>) security.get("password");
    }
}
