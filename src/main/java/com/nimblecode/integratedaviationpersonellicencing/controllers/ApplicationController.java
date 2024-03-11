package com.nimblecode.integratedaviationpersonellicencing.controllers;

import com.nimblecode.integratedaviationpersonellicencing.models.consumables.ConsumableApplication;
import com.nimblecode.integratedaviationpersonellicencing.models.entities.Application;
import com.nimblecode.integratedaviationpersonellicencing.models.transferables.TransferableApplication;
import com.nimblecode.integratedaviationpersonellicencing.service.ApplicationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationsService applicationsService;

    @PostMapping("/create-application")
    public TransferableApplication createApplication(@RequestBody ConsumableApplication consumable){
        return applicationsService.createApplication(consumable).serializeForTransfer();
    }

    @PutMapping("/complete-a-check")
    public TransferableApplication completeACheck(@RequestParam String applicationId,@RequestParam String check){
        return applicationsService.completeACheck(applicationId,check).serializeForTransfer();
    }

    @PutMapping("/comment")
    public TransferableApplication comment(@RequestParam String applicationId,@RequestParam String message){
        return applicationsService.comment(applicationId,message).serializeForTransfer();
    }

    @GetMapping("/available-applications")
    public List<TransferableApplication> availableApplications(){
        return applicationsService.availableApplications().stream().map(Application::serializeForTransfer).toList();
    }
}
