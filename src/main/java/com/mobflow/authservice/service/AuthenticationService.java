package com.mobflow.authservice.service;

import com.mobflow.authservice.model.DTO.request.RegisterRequest;
import com.mobflow.authservice.model.entities.UserCredential;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserCredentialService userCredentialService;

    public AuthenticationService(UserCredentialService userCredentialService){
        this.userCredentialService = userCredentialService;
    }
    public UserCredential register(RegisterRequest registerRequest){
        return userCredentialService.saveCredential(registerRequest);
    }
}
