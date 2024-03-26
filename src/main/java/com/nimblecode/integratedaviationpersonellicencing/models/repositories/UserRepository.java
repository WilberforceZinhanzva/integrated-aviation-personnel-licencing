package com.nimblecode.integratedaviationpersonellicencing.models.repositories;

import com.nimblecode.integratedaviationpersonellicencing.models.entities.Role;
import com.nimblecode.integratedaviationpersonellicencing.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    boolean existsByUsernameIgnoreCase(String username);
    Optional<User> findByUsername(String username);

}
