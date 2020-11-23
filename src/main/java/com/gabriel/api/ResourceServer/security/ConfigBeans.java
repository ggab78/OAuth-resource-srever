package com.gabriel.api.ResourceServer.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@Configuration
public class ConfigBeans {

    @Bean
    KeycloakRoleConverter keycloakRoleConverter(){
        return new KeycloakRoleConverter();
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter(KeycloakRoleConverter keycloakRoleConverter) {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(keycloakRoleConverter);
        return jwtAuthenticationConverter;

    }

}
