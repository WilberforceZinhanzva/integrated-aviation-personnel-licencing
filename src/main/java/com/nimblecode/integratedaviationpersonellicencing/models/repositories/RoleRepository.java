package com.nimblecode.integratedaviationpersonellicencing.models.repositories;

import com.nimblecode.integratedaviationpersonellicencing.models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,String> {
    boolean existsByNameIgnoreCase(String name);
    Optional<Role> findByName(String name);
    List<Role> findAllByNameIn(List<String> names);
}
