package com.gabriel.api.ResourceServer.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@Slf4j
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final JwtAuthenticationConverter jwtAuthenticationConverter;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

                http
                        .authorizeRequests()
                                .antMatchers(HttpMethod.GET, "/users/status/check")
                        //.hasAuthority("SCOPE_profile")
                        //.hasRole("developer")
                        .hasAuthority("ROLE_developer")
                        .anyRequest().authenticated()
                        .and()
                        .oauth2ResourceServer()
                        .jwt()
                        .jwtAuthenticationConverter(jwtAuthenticationConverter);
    }
}
