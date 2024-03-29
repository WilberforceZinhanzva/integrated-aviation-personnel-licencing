package com.nimblecode.integratedaviationpersonellicencing.models.consumables;

import lombok.Data;

import java.util.List;

@Data
public class ConsumableApplicationType {
    private String name;
    private List<ConsumableApplicationCheck> applicationCheckList;
}
