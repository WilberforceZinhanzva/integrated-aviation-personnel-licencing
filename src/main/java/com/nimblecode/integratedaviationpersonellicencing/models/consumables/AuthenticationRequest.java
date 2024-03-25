package com.nimblecode.integratedaviationpersonellicencing.models.consumables;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String username;
    private String password;
}
