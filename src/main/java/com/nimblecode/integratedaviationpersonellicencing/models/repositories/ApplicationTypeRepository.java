package com.nimblecode.integratedaviationpersonellicencing.models.repositories;

import com.nimblecode.integratedaviationpersonellicencing.models.entities.ApplicationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationTypeRepository extends JpaRepository<ApplicationType,String> {
    boolean existsByNameIgnoreCase(String name);
}
