package com.gabriel.api.ResourceServer.web.controllers;

import com.gabriel.api.ResourceServer.domain.RestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    Environment env;

    @PermitAll
    @GetMapping("/status/check")
    public String status() {
        return "working on port "+env.getProperty("local.server.port");
    }

    @Secured("ROLE_developer")
    @DeleteMapping(path="/{id}")
    public String delete(@PathVariable String id){
        return "deleting user with id "+id;
    }


    @PreAuthorize("hasRole('ROLE_developer') or #id == #jwt.subject")
    @PutMapping(path="/user/{id}")
    public String update(@PathVariable String id, @AuthenticationPrincipal Jwt jwt){
        return "updating user with id "+id;
    }


    @PostAuthorize("returnObject.userId == #jwt.subject")
    @GetMapping(path = "/{id}")
    public RestUser getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        return RestUser.builder().userId(id).firstName("gab").lastName("gam").build();
    }
}
