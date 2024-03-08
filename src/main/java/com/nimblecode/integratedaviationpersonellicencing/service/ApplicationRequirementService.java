package com.nimblecode.integratedaviationpersonellicencing.service;

import com.nimblecode.integratedaviationpersonellicencing.exceptions.GenericException;
import com.nimblecode.integratedaviationpersonellicencing.models.consumables.ConsumableApplicationRequirement;
import com.nimblecode.integratedaviationpersonellicencing.models.entities.ApplicationRequirement;
import com.nimblecode.integratedaviationpersonellicencing.models.repositories.ApplicationRequirementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationRequirementService {

    private final ApplicationRequirementRepository applicationRequirementRepository;

    public ApplicationRequirement addApplicationRequirement(ConsumableApplicationRequirement consumable){
        if(applicationRequirementRepository.existsByRequirementIgnoreCaseAndApplicationTypeIgnoreCase(consumable.getRequirement(), consumable.getApplicationType()))
            throw new GenericException("Requirement already exists");

        ApplicationRequirement applicationRequirement = new ApplicationRequirement();
        applicationRequirement.setRequirement(consumable.getRequirement());
        applicationRequirement.setApplicationType(consumable.getApplicationType());
        return applicationRequirementRepository.save(applicationRequirement);
    }

    public List<ApplicationRequirement> applicationRequirements(String applicationType){
        return applicationRequirementRepository.findAllByApplicationTypeIgnoreCase(applicationType);
    }


}
