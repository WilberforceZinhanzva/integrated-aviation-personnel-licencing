package com.nimblecode.integratedaviationpersonellicencing.service;

import com.nimblecode.integratedaviationpersonellicencing.exceptions.GenericException;
import com.nimblecode.integratedaviationpersonellicencing.models.consumables.ConsumableUser;
import com.nimblecode.integratedaviationpersonellicencing.models.entities.*;
import com.nimblecode.integratedaviationpersonellicencing.models.repositories.*;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRolesRepository userRolesRepository;
    private final UserPermissionRepository userPermissionRepository;


    @Transactional
    public User addUser(ConsumableUser consumable){
        if(userRepository.existsByUsernameIgnoreCase(consumable.getUsername()))
            throw new GenericException("Username already taken");
        List<Role> roles = roleRepository.findAllByNameIn(consumable.getRoles());
        List<Permission> permissions = permissionRepository.findAllByNameIn(consumable.getPermissions());

        User user = new User();
        user.setUsername(consumable.getUsername());
        user.setFullname(consumable.getFullname());
        user.setPassword(passwordEncoder.encode(consumable.getPassword()));
        user.setRoles(roles);
        user.setPermissions(permissions);


        List<UserRole> userRoles = new ArrayList<>();
        for(Role role : roles){
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(role.getId());
            userRoles.add(userRole);
        }
        List<UserPermission> userPermissions = new ArrayList<>();
        for(Permission permission : permissions){
            UserPermission userPermission = new UserPermission();
            userPermission.setUserId(user.getId());
            userPermission.setPermissionId(permission.getId());
            userPermissions.add(userPermission);
        }

        userRolesRepository.saveAll(userRoles);
        userPermissionRepository.saveAll(userPermissions);

        return userRepository.save(user);
    }

    public List<User> availableUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public void initialize(){
        if(!userRepository.existsByUsernameIgnoreCase("admin")){
            User user = new User();
            user.setFullname("System Admin Account");
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin"));



            Optional<Role> adminRole = roleRepository.findByName("Admin");
            if(adminRole.isEmpty())
                throw new GenericException("Admin role not found");
            user.setRoles(new ArrayList<>());
            user.getRoles().add(adminRole.get());

            UserRole userRole = new UserRole();
            userRole.setRoleId(adminRole.get().getId());
            userRole.setUserId(user.getId());

            userRolesRepository.save(userRole);

            userRepository.save(user);
        }
    }




}
