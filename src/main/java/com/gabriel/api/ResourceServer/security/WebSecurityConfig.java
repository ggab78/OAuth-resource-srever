package com.gabriel.api.ResourceServer.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Slf4j
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {

                http
                        .authorizeRequests()
                                .antMatchers(HttpMethod.GET, "/users/status/check").hasAuthority("SCOPE_profile")
                        .anyRequest().authenticated()
                        .and()
                        .oauth2ResourceServer()
                        .jwt();
    }



}
