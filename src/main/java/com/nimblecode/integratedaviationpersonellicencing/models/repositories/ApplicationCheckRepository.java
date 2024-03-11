package com.nimblecode.integratedaviationpersonellicencing.models.repositories;

import com.nimblecode.integratedaviationpersonellicencing.models.entities.ApplicationCheck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationCheckRepository extends JpaRepository<ApplicationCheck,String> {
    List<ApplicationCheck> findAllByApplicationType_IdAndPermittedRolesContaining(String applicationTypeId, String role);
    List<ApplicationCheck> findAllByApplicationType_NameIgnoreCase(String applicationType);
    Optional<ApplicationCheck> findByApplicationType_NameIgnoreCaseAndRequiredCheckIgnoreCase(String applicationTypeName, String check);
}
