package com.nimblecode.integratedaviationpersonellicencing.controllers;

import com.nimblecode.integratedaviationpersonellicencing.models.consumables.ConsumableUser;
import com.nimblecode.integratedaviationpersonellicencing.models.entities.User;
import com.nimblecode.integratedaviationpersonellicencing.models.transferables.TransferableUser;
import com.nimblecode.integratedaviationpersonellicencing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {


    private final UserService userService;

    @PostMapping("/add-user")
    public TransferableUser addUser(@RequestBody ConsumableUser consumable){
        return userService.addUser(consumable).serializeForTransfer();
    }

    @GetMapping("/available-users")
    public List<TransferableUser> availableUsers(){
        return userService.availableUsers().stream().map(User::serializeForTransfer).collect(Collectors.toList());
    }
}
