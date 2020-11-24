package com.gabriel.api.ResourceServer.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestUser {

    private String userId;
    private String firstName;
    private String lastName;
}
