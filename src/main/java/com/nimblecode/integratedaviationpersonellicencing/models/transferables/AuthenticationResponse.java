package com.nimblecode.integratedaviationpersonellicencing.models.transferables;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AuthenticationResponse {
    private String username;
    private List<String> roles;
    private String token;
}
