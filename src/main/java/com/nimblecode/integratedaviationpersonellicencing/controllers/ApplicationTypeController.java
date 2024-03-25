package com.nimblecode.integratedaviationpersonellicencing.controllers;

import com.nimblecode.integratedaviationpersonellicencing.models.consumables.ConsumableApplicationType;
import com.nimblecode.integratedaviationpersonellicencing.models.entities.ApplicationCheck;
import com.nimblecode.integratedaviationpersonellicencing.models.entities.ApplicationType;
import com.nimblecode.integratedaviationpersonellicencing.models.transferables.TransferableApplicationCheck;
import com.nimblecode.integratedaviationpersonellicencing.models.transferables.TransferableApplicationType;
import com.nimblecode.integratedaviationpersonellicencing.service.ApplicationTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/application-types")
public class ApplicationTypeController {

    private final ApplicationTypeService applicationTypeService;


    @PostMapping("/add-application-type")
    public TransferableApplicationType addApplicationType(@RequestBody ConsumableApplicationType consumable){
        return applicationTypeService.addApplicationType(consumable).serializeForTransfer();
    }

    @GetMapping("/available-application-types")
    public List<TransferableApplicationType> availableApplicationTypes(){
        return applicationTypeService.availableApplicationTypes().stream().map(ApplicationType::serializeForTransfer).toList();
    }

    @GetMapping("/find-allowed-application-checks")
    public List<TransferableApplicationCheck> findAllowedApplicationChecks(@RequestParam String applicationTypeId,@RequestParam String role){
        return applicationTypeService.findAllowedApplicationChecks(applicationTypeId,role).stream().map(ApplicationCheck::serializeForTransfer).toList();
    }

}
