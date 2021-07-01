package com.roomies.roomies.controller;


import com.roomies.roomies.domain.model.User;
import com.roomies.roomies.domain.service.UserService;
import com.roomies.roomies.resource.SaveUserResource;
import com.roomies.roomies.resource.UserResource;
import com.roomies.roomies.service.communication.AuthenticationRequest;
import com.roomies.roomies.service.communication.AuthenticationResponse;
import com.roomies.roomies.util.JwtCenter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtCenter tokenCenter;
    @Autowired
    private UserService userService;

    @PostMapping("/sign-in")
    public ResponseEntity<?> generateAuthenticationToken(
            @RequestBody AuthenticationRequest request)
            throws Exception {
        authenticate(request.getUsername(), request.getPassword());
        final UserDetails userDetails =
                userService.loadUserByUsername(request.getUsername());
        System.out.println("Password: " + request.getPassword());
        String token = tokenCenter.generateToken(userDetails);
        return ResponseEntity.ok(new
                AuthenticationResponse(userDetails.getUsername(), token));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> generateAuthenticationToken(
            @RequestBody SaveUserResource resource)
            throws Exception {
        return ResponseEntity.ok(convertToResource(userService.createUser(convertToEntity(resource))));
    }

    private void authenticate(String username, String password)
            throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


    private User convertToEntity(SaveUserResource resource) {
        return mapper.map(resource, User.class);
    }

    private UserResource convertToResource(User entity){
        return mapper.map(entity, UserResource.class);
    }
}