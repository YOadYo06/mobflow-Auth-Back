package com.mobflow.authservice.controller;

import com.mobflow.authservice.model.DTO.request.RegisterRequest;
import com.mobflow.authservice.model.entities.UserCredential;
import com.mobflow.authservice.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<UserCredential> register(@RequestBody RegisterRequest registerUserDto) {
        UserCredential registeredUser = authenticationService.register(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }
}
