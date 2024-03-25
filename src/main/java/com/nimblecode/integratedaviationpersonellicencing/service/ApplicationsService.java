package com.nimblecode.integratedaviationpersonellicencing.service;

import com.nimblecode.integratedaviationpersonellicencing.enums.ApplicationStatus;
import com.nimblecode.integratedaviationpersonellicencing.exceptions.GenericException;
import com.nimblecode.integratedaviationpersonellicencing.models.consumables.ConsumableApplication;
import com.nimblecode.integratedaviationpersonellicencing.models.entities.*;
import com.nimblecode.integratedaviationpersonellicencing.models.repositories.ApplicationCheckRepository;
import com.nimblecode.integratedaviationpersonellicencing.models.repositories.ApplicationRepository;
import com.nimblecode.integratedaviationpersonellicencing.models.repositories.AssignedApplicationRepository;
import com.nimblecode.integratedaviationpersonellicencing.utility.Util;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationsService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationCheckRepository applicationCheckRepository;
    private final AssignedApplicationRepository assignedApplicationRepository;


    @Transactional
    public Application createApplication(ConsumableApplication consumable){
         Application application = new Application();
         application.setApplicationRef(Util.generateReference());
         application.setApplicantName(consumable.getApplicantName());
         application.setApplicationType(consumable.getApplicationType());
         application.setApplicationStatus(ApplicationStatus.PENDING);

        List<ApplicationCheck> pendingChecks = applicationCheckRepository.findAllByApplicationType_NameIgnoreCase(consumable.getApplicationType());
        application.addPendingChecks(pendingChecks.stream().map(ApplicationCheck::getRequiredCheck).toList());

        assignApplication(application.getId(),Util.loggedInUser());
        return applicationRepository.save(application);
    }

    public Application completeACheck(String applicationId,String check){
        Optional<Application> application = applicationRepository.findById(applicationId);
        if(application.isEmpty())
            throw new GenericException("Application not found");

        Optional<ApplicationCheck> applicationCheck = applicationCheckRepository.findByApplicationType_NameIgnoreCaseAndRequiredCheckIgnoreCase(application.get().getApplicationType(),check);
        if(applicationCheck.isEmpty())
            throw new GenericException("Application check not found");

        CompletedCheck completedCheck = new CompletedCheck();
        completedCheck.setCheckId(applicationCheck.get().getId());
        completedCheck.setCheckedBy(Util.loggedInUser());
        completedCheck.setApplication(application.get());
        application.get().getCompletedCheckList().add(completedCheck);

        application.get().removeChecks(Arrays.asList(applicationCheck.get().getRequiredCheck()));

        if(application.get().getPendingChecks().isEmpty())
            application.get().setApplicationStatus(ApplicationStatus.COMPLETED);

        return applicationRepository.save(application.get());
    }

    public Application comment(String applicationId, String message){
        Optional<Application> application = applicationRepository.findById(applicationId);
        if(application.isEmpty())
            throw new GenericException("Application not found");

        Comment c = new Comment();
        c.setMessage(message);
        c.setCommentedBy(Util.loggedInUser());

        application.get().getComments().add(c);
        return applicationRepository.save(application.get());
    }

    public List<Application> availableApplications(){
        return applicationRepository.findAll();
    }
    public AssignedApplication assignApplication(String applicationId, String assignee){

        Optional<AssignedApplication> assignedApplication = assignedApplicationRepository.findByApplicationId(applicationId);
        if(assignedApplication.isEmpty()){
            AssignedApplication a = new AssignedApplication();
            a.setApplicationId(applicationId);
            a.setAssignedTo(assignee);
            a.setAssignedBy(Util.loggedInUser());
            a.setAssignedOn(LocalDateTime.now());
            return assignedApplicationRepository.save(a);
        }

        assignedApplication.get().setAssignedTo(assignee);
        assignedApplication.get().setAssignedBy(Util.loggedInUser());
        assignedApplication.get().setAssignedOn(LocalDateTime.now());
        return assignedApplicationRepository.save(assignedApplication.get());
    }

    public List<Application> assignedApplications(){
        log.info(Util.loggedInUser());
        if(Util.loggedInUser().contentEquals("admin"))
            return applicationRepository.findAll();

        List<AssignedApplication> assignments = assignedApplicationRepository.findAllByAssignedTo(Util.loggedInUser());
        List<String> applicationsIds = assignments.stream().map(AssignedApplication::getApplicationId).toList();
        return applicationRepository.findAllByIdIn(applicationsIds);
    }
}
