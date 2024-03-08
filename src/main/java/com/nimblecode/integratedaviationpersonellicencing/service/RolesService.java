package com.nimblecode.integratedaviationpersonellicencing.service;

import com.nimblecode.integratedaviationpersonellicencing.models.entities.Role;
import com.nimblecode.integratedaviationpersonellicencing.models.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RolesService {

    private final RoleRepository roleRepository;

    public List<Role> availableRoles(){
        return roleRepository.findAll();
    }

    public void initialize(){
        List<String> roleNames = Arrays.asList("Admin","Applicant","Receiving Licencing Officer","Verification Licencing Officer","Airworthiness Inspector Secretary","Air Worthiness Manager","Airworthiness Inspector","Licencing Manager","Licencing Officer");

        List<Role> roles =new ArrayList<>();
        for(String name : roleNames){
            if(!roleRepository.existsByNameIgnoreCase(name)){
                Role role = new Role();
                role.setName(name);
                roles.add(role);
            }else{
                log.info("[::]Role "+ name+ " skipped because it already exists");
            }
        }
        roleRepository.saveAll(roles);

    }
}
