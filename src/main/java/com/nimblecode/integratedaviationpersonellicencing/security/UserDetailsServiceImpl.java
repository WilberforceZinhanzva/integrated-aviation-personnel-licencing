package com.nimblecode.integratedaviationpersonellicencing.security;

import com.nimblecode.integratedaviationpersonellicencing.exceptions.GenericException;
import com.nimblecode.integratedaviationpersonellicencing.models.entities.Role;
import com.nimblecode.integratedaviationpersonellicencing.models.entities.User;
import com.nimblecode.integratedaviationpersonellicencing.models.entities.UserRole;
import com.nimblecode.integratedaviationpersonellicencing.models.repositories.RoleRepository;
import com.nimblecode.integratedaviationpersonellicencing.models.repositories.UserRepository;
import com.nimblecode.integratedaviationpersonellicencing.models.repositories.UserRolesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserRolesRepository userRolesRepository;
    private final RoleRepository roleRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Loading user by username...");
        Optional<User> user = userRepository.findByUsername(username);

        List<UserRole> userRoleList = userRolesRepository.findAllByUserId(user.get().getId());
        List<Role> roles = roleRepository.findAllByIdIn(userRoleList.stream().map(UserRole::getRoleId).toList());
        user.get().setRoles(roles);
        if(user.isEmpty())
            throw new GenericException("Credential not found");
        return  user.get();
    }
}
