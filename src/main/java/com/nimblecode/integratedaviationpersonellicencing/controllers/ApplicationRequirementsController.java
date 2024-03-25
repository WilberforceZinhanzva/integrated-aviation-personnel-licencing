package com.nimblecode.integratedaviationpersonellicencing.controllers;

import com.nimblecode.integratedaviationpersonellicencing.models.consumables.ConsumableApplicationRequirement;
import com.nimblecode.integratedaviationpersonellicencing.models.entities.ApplicationRequirement;
import com.nimblecode.integratedaviationpersonellicencing.models.transferables.TransferableApplicationRequirement;
import com.nimblecode.integratedaviationpersonellicencing.service.ApplicationRequirementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/application-requirements")
public class ApplicationRequirementsController {

    private ApplicationRequirementService applicationRequirementService;

    @PostMapping("/add-application-requirement")
    public TransferableApplicationRequirement addApplicationRequirement(@RequestBody ConsumableApplicationRequirement consumable){
        return applicationRequirementService.addApplicationRequirement(consumable).serializeForTransfer();
    }

    @GetMapping("/{applicationType}")
    public List<TransferableApplicationRequirement> applicationRequirements(@PathVariable String applicationType){
        return applicationRequirementService.applicationRequirements(applicationType).stream().map(ApplicationRequirement::serializeForTransfer).toList();
    }
}
