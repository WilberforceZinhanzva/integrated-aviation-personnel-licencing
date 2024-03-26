package com.nimblecode.integratedaviationpersonellicencing.models.repositories;

import com.nimblecode.integratedaviationpersonellicencing.models.entities.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPermissionRepository extends JpaRepository<UserPermission,String> {
    List<UserPermission> findAllByUserId(String userId);
}
