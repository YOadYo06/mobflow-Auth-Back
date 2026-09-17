package com.mobflow.authservice.service;

import com.mobflow.authservice.exception.GenericApplicationException;
import com.mobflow.authservice.model.DTO.request.RegisterRequest;
import com.mobflow.authservice.model.entities.UserCredential;
import com.mobflow.authservice.model.enums.ErrorTP;
import com.mobflow.authservice.repository.UserCredentialRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserCredentialService {
    private static final long CONFIRMATION_TOKEN_TTL_HOURS = 24;

    private final PasswordEncoder passwordEncoder;
    private final UserCredentialRepository userCredentialRepository;

    public UserCredentialService(PasswordEncoder passwordEncoder, UserCredentialRepository userCredentialRepository){
        this.passwordEncoder = passwordEncoder;
        this.userCredentialRepository = userCredentialRepository;
    }

    private void ensureUsernameIsAvailable(String username){
        if(userCredentialRepository.findByUsername(username).isPresent()){
            throw new GenericApplicationException(ErrorTP.USERNAME_ALREADY_EXIST);
        }
    }
    private void ensureEmailIsAvailable(String username){
        if(userCredentialRepository.findByUsername(username).isPresent()){
            throw new GenericApplicationException(ErrorTP.EMAIL_ALREADY_EXIST);
        }
    }
    public UserCredential saveCredential(RegisterRequest registerRequest){
        ensureUsernameIsAvailable(registerRequest.getUsername());
        ensureEmailIsAvailable(registerRequest.getEmail());

        UserCredential userCredential = UserCredential.createUserCredential(
                registerRequest.getUsername(),
                registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword()));

        userCredential.setConfirmationToken(UUID.randomUUID().toString());
        userCredential.setConfirmationTokenExpiresAt(LocalDateTime.now().plusHours(CONFIRMATION_TOKEN_TTL_HOURS));

        return userCredentialRepository.save(userCredential);
    }
}
