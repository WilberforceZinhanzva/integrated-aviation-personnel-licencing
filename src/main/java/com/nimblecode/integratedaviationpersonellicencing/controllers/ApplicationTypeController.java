package com.nimblecode.integratedaviationpersonellicencing.controllers;

import com.nimblecode.integratedaviationpersonellicencing.models.consumables.ConsumableApplicationType;
import com.nimblecode.integratedaviationpersonellicencing.models.entities.ApplicationType;
import com.nimblecode.integratedaviationpersonellicencing.models.transferables.TransferableApplicationType;
import com.nimblecode.integratedaviationpersonellicencing.service.ApplicationTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/application-types")
public class ApplicationTypeController {

    private final ApplicationTypeService applicationTypeService;


    @PostMapping("/add-application-type")
    public TransferableApplicationType addApplicationType(ConsumableApplicationType consumable){
        return applicationTypeService.addApplicationType(consumable).serializeForTransfer();
    }

    @GetMapping("/available-application-types")
    public List<TransferableApplicationType> availableApplicationTypes(){
        return applicationTypeService.availableApplicationTypes().stream().map(ApplicationType::serializeForTransfer).toList();
    }

}
