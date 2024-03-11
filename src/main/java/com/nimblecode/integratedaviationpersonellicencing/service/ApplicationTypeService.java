package com.nimblecode.integratedaviationpersonellicencing.service;

import com.nimblecode.integratedaviationpersonellicencing.exceptions.GenericException;
import com.nimblecode.integratedaviationpersonellicencing.models.consumables.ConsumableApplicationCheck;
import com.nimblecode.integratedaviationpersonellicencing.models.consumables.ConsumableApplicationType;
import com.nimblecode.integratedaviationpersonellicencing.models.entities.ApplicationCheck;
import com.nimblecode.integratedaviationpersonellicencing.models.entities.ApplicationType;
import com.nimblecode.integratedaviationpersonellicencing.models.repositories.ApplicationCheckRepository;
import com.nimblecode.integratedaviationpersonellicencing.models.repositories.ApplicationTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationTypeService {

    private final ApplicationTypeRepository applicationTypeRepository;
    private final ApplicationCheckRepository applicationCheckRepository;


    public ApplicationType addApplicationType(ConsumableApplicationType consumable){
        if(applicationTypeRepository.existsByNameIgnoreCase(consumable.getName()))
            throw new GenericException("Application type already exists");

        ApplicationType applicationType = new ApplicationType();
        applicationType.setName(consumable.getName());

        for(ConsumableApplicationCheck consumableApplicationCheck : consumable.getApplicationCheckList()){
            ApplicationCheck applicationCheck = new ApplicationCheck();
            applicationCheck.setRequiredCheck(consumableApplicationCheck.getCheck());
            applicationCheck.addPermittedRoles(consumableApplicationCheck.getPermittedRoles());
            applicationCheck.setApplicationType(applicationType);
            applicationType.getApplicationChecks().add(applicationCheck);
        }

        return applicationTypeRepository.save(applicationType);
    }

    public List<ApplicationCheck> findAllowedApplicationChecks(String applicationTypeId,String role){
        return applicationCheckRepository.findAllByApplicationType_IdAndPermittedRolesContaining(applicationTypeId,role);
    }

    public List<ApplicationType> availableApplicationTypes(){
        return applicationTypeRepository.findAll();
    }

}
