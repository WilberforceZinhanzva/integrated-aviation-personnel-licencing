package com.nimblecode.integratedaviationpersonellicencing.models.repositories;

import com.nimblecode.integratedaviationpersonellicencing.models.entities.AssignedApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssignedApplicationRepository extends JpaRepository<AssignedApplication,String> {
    Optional<AssignedApplication> findByApplicationId(String applicationId);
    List<AssignedApplication> findAllByAssignedTo(String assignedTo);
}
