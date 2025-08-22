package com.evaitcsmatt.shophub.webserver.services;

import com.evaitcsmatt.shophub.webserver.dtos.AuthRequest;
import com.evaitcsmatt.shophub.webserver.entities.UserCredential;
import com.evaitcsmatt.shophub.webserver.repositories.UserCredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCredentialService {
    private final UserCredentialRepository userCredentialRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public String createUserCredentials(AuthRequest request) {
        UserCredential userCredential = UserCredential.builder()
                .email(request.email().toLowerCase())
                .password(passwordEncoder.encode(request.password()))
                .role("USER")
                .build();
        userCredentialRepository.save(userCredential);
        return "User Credential Created Successfully";
    }

    public Authentication login(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        return authentication;
    }
}
