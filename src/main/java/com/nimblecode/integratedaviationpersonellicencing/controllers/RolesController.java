package com.nimblecode.integratedaviationpersonellicencing.controllers;

import com.nimblecode.integratedaviationpersonellicencing.models.entities.Role;
import com.nimblecode.integratedaviationpersonellicencing.models.transferables.TransferableRole;
import com.nimblecode.integratedaviationpersonellicencing.service.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RolesController {

    private final RolesService rolesService;

    @GetMapping
    public List<TransferableRole> availableRoles(){
        return rolesService.availableRoles().stream().map(Role::serializeForTransfer).collect(Collectors.toList());
    }
}
