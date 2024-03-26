package com.nimblecode.integratedaviationpersonellicencing.models.repositories;

import com.nimblecode.integratedaviationpersonellicencing.models.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRolesRepository extends JpaRepository<UserRole,String> {
    List<UserRole> findAllByUserId(String userId);
}
