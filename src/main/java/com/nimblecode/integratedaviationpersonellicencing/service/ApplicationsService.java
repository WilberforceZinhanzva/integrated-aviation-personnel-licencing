package com.nimblecode.integratedaviationpersonellicencing.service;

import com.nimblecode.integratedaviationpersonellicencing.enums.ApplicationStatus;
import com.nimblecode.integratedaviationpersonellicencing.exceptions.GenericException;
import com.nimblecode.integratedaviationpersonellicencing.models.consumables.ConsumableApplication;
import com.nimblecode.integratedaviationpersonellicencing.models.entities.Application;
import com.nimblecode.integratedaviationpersonellicencing.models.entities.ApplicationCheck;
import com.nimblecode.integratedaviationpersonellicencing.models.entities.Comment;
import com.nimblecode.integratedaviationpersonellicencing.models.entities.CompletedCheck;
import com.nimblecode.integratedaviationpersonellicencing.models.repositories.ApplicationCheckRepository;
import com.nimblecode.integratedaviationpersonellicencing.models.repositories.ApplicationRepository;
import com.nimblecode.integratedaviationpersonellicencing.utility.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationsService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationCheckRepository applicationCheckRepository;


    public Application createApplication(ConsumableApplication consumable){
         Application application = new Application();
         application.setApplicationRef(Util.generateReference());
         application.setApplicantName(consumable.getApplicantName());
         application.setApplicationType(consumable.getApplicationType());
         application.setApplicationStatus(ApplicationStatus.PENDING);

        List<ApplicationCheck> pendingChecks = applicationCheckRepository.findAllByApplicationType_NameIgnoreCase(consumable.getApplicationType());
        application.addPendingChecks(pendingChecks.stream().map(ApplicationCheck::getRequiredCheck).toList());

        return applicationRepository.save(application);
    }

    public Application completeACheck(String applicationId,String check){
        String principal = "SYSTEM";
        Optional<Application> application = applicationRepository.findById(applicationId);
        if(application.isEmpty())
            throw new GenericException("Application not found");

        Optional<ApplicationCheck> applicationCheck = applicationCheckRepository.findByApplicationType_NameIgnoreCaseAndRequiredCheckIgnoreCase(application.get().getApplicationType(),check);
        if(applicationCheck.isEmpty())
            throw new GenericException("Application check not found");

        CompletedCheck completedCheck = new CompletedCheck();
        completedCheck.setCheckId(applicationCheck.get().getId());
        completedCheck.setCheckedBy(principal);
        completedCheck.setApplication(application.get());
        application.get().getCompletedCheckList().add(completedCheck);

        application.get().removeChecks(Arrays.asList(applicationCheck.get().getRequiredCheck()));

        return applicationRepository.save(application.get());
    }

    public Application comment(String applicationId, String message){
        String principal = "SYSTEM";
        Optional<Application> application = applicationRepository.findById(applicationId);
        if(application.isEmpty())
            throw new GenericException("Application not found");

        Comment c = new Comment();
        c.setMessage(message);
        c.setCommentedBy(principal);

        application.get().getComments().add(c);
        return applicationRepository.save(application.get());
    }

    public List<Application> availableApplications(){
        return applicationRepository.findAll();
    }
}
