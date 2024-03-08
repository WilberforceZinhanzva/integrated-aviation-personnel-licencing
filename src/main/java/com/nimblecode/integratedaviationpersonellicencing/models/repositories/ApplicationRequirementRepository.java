package com.nimblecode.integratedaviationpersonellicencing.models.repositories;

import com.nimblecode.integratedaviationpersonellicencing.models.entities.ApplicationRequirement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRequirementRepository extends JpaRepository<ApplicationRequirement,String> {
    boolean existsByRequirementIgnoreCaseAndApplicationTypeIgnoreCase(String requirement,String applicationType);
    List<ApplicationRequirement> findAllByApplicationTypeIgnoreCase(String applicationType);
}
