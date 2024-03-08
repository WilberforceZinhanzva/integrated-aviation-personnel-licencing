package com.nimblecode.integratedaviationpersonellicencing.models.consumables;

import lombok.Data;

import java.util.List;

@Data
public class ConsumableUser {
    private String fullname;
    private String username;
    private String password;
    private List<String> roles;
    private List<String> permissions;
}
