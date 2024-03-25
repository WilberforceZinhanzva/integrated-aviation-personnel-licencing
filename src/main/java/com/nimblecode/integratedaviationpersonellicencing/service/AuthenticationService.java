package com.nimblecode.integratedaviationpersonellicencing.service;

import com.nimblecode.integratedaviationpersonellicencing.exceptions.GenericException;
import com.nimblecode.integratedaviationpersonellicencing.models.consumables.AuthenticationRequest;
import com.nimblecode.integratedaviationpersonellicencing.models.entities.Role;
import com.nimblecode.integratedaviationpersonellicencing.models.entities.User;
import com.nimblecode.integratedaviationpersonellicencing.models.repositories.UserRepository;
import com.nimblecode.integratedaviationpersonellicencing.models.transferables.AuthenticationResponse;
import com.nimblecode.integratedaviationpersonellicencing.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.core.Authentication;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        Optional<User> user = userRepository.findByUsername(request.getUsername());
        if (user.isEmpty())
            throw new GenericException("User not found");

        if(authentication.isAuthenticated())
            return AuthenticationResponse.builder()
                    .username(user.get().getUsername())
                    .roles(user.get().getRoles().stream().map(Role::getName).toList())
                    .token(jwtService.GenerateToken(request.getUsername()))
                    .build();
        else
            throw new GenericException("Invalid user request");

    }
}
