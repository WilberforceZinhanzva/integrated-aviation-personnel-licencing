package com.nimblecode.integratedaviationpersonellicencing.service;

import com.nimblecode.integratedaviationpersonellicencing.exceptions.GenericException;
import com.nimblecode.integratedaviationpersonellicencing.models.consumables.ConsumableApplicationType;
import com.nimblecode.integratedaviationpersonellicencing.models.entities.ApplicationType;
import com.nimblecode.integratedaviationpersonellicencing.models.repositories.ApplicationTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationTypeService {

    private final ApplicationTypeRepository applicationTypeRepository;


    public ApplicationType addApplicationType(ConsumableApplicationType consumable){
        if(applicationTypeRepository.existsByNameIgnoreCase(consumable.getName()))
            throw new GenericException("Application type already exists");

        ApplicationType applicationType = new ApplicationType();
        applicationType.setName(consumable.getName());

        return applicationTypeRepository.save(applicationType);
    }

    public List<ApplicationType> availableApplicationTypes(){
        return applicationTypeRepository.findAll();
    }

}
