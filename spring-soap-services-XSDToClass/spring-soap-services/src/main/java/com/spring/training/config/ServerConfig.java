package com.spring.training.config;

import lombok.Data;

import java.util.Map;

@Data
public class ServerConfig {

    Map<String, Object> security;

    public Map<String, String> getCertificate() {
        return (Map<String, String>) security.get("certificate");
    }

    public Map<String, String> getPassword() {
        return (Map<String, String>) security.get("password");
    }

    public Map<String, String> getUsers() {
        Map<String, Object> password = (Map<String, Object>) security.get("password");
        return (Map<String, String>) password.get("users");
    }
}
