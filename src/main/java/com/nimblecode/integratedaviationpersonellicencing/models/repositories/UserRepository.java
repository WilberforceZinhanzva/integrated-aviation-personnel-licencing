package com.nimblecode.integratedaviationpersonellicencing.models.repositories;

import com.nimblecode.integratedaviationpersonellicencing.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    boolean existsByUsernameIgnoreCase(String username);
}
