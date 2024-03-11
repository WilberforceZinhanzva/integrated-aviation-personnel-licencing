package com.nimblecode.integratedaviationpersonellicencing.models.consumables;

import lombok.Data;

import java.util.List;

@Data
public class ConsumableApplicationCheck {
    private String check;
    private List<String> permittedRoles;
}
