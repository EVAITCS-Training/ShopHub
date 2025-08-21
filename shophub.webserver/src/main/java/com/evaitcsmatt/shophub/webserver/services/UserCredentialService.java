package com.evaitcsmatt.shophub.webserver.services;

import com.evaitcsmatt.shophub.webserver.dtos.AuthRequest;
import com.evaitcsmatt.shophub.webserver.entities.UserCredential;
import com.evaitcsmatt.shophub.webserver.repositories.UserCredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCredentialService {
    private final UserCredentialRepository userCredentialRepository;
    private final PasswordEncoder passwordEncoder;

    public String createUserCredentials(AuthRequest request) {
        UserCredential userCredential = UserCredential.builder()
                .email(request.email().toLowerCase())
                .password(passwordEncoder.encode(request.password()))
                .role("USER")
                .build();
        userCredentialRepository.save(userCredential);
        return "User Credential Created Successfully";
    }
}
