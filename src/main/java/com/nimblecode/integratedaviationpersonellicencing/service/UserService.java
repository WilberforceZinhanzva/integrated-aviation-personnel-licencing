package com.nimblecode.integratedaviationpersonellicencing.service;

import com.nimblecode.integratedaviationpersonellicencing.exceptions.GenericException;
import com.nimblecode.integratedaviationpersonellicencing.models.consumables.ConsumableUser;
import com.nimblecode.integratedaviationpersonellicencing.models.entities.Permission;
import com.nimblecode.integratedaviationpersonellicencing.models.entities.Role;
import com.nimblecode.integratedaviationpersonellicencing.models.entities.User;
import com.nimblecode.integratedaviationpersonellicencing.models.repositories.PermissionRepository;
import com.nimblecode.integratedaviationpersonellicencing.models.repositories.RoleRepository;
import com.nimblecode.integratedaviationpersonellicencing.models.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
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


    public User addUser(ConsumableUser consumable){
        if(userRepository.existsByUsernameIgnoreCase(consumable.getUsername()))
            throw new GenericException("Username already taken");
        List<Role> roles = roleRepository.findAllByNameIn(consumable.getRoles());
        List<Permission> permissions = permissionRepository.findAllByNameIn(consumable.getPermissions());

        User user = new User();
        user.setUsername(consumable.getUsername());
        user.setFullname(consumable.getFullname());
        user.setPassword(consumable.getPassword());
        user.setRoles(roles);
        user.setPermissions(permissions);
        return userRepository.save(user);
    }

    public List<User> availableUsers(){
        return userRepository.findAll();
    }

    public void initialize(){
        if(!userRepository.existsByUsernameIgnoreCase("admin")){
            User user = new User();
            user.setFullname("System Admin Account");
            user.setUsername("admin");
            user.setPassword("admin");


            Optional<Role> adminRole = roleRepository.findByName("Admin");
            if(adminRole.isEmpty())
                throw new GenericException("Admin role not found");
            user.setRoles(new ArrayList<>());
            user.getRoles().add(adminRole.get());

            userRepository.save(user);
        }
    }




}
