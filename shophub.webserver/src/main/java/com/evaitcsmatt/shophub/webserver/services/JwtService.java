package com.evaitcsmatt.shophub.webserver.services;

import com.evaitcsmatt.shophub.webserver.config.JwtConfigProps;
import com.evaitcsmatt.shophub.webserver.entities.UserCredential;
import com.evaitcsmatt.shophub.webserver.repositories.UserCredentialRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final UserCredentialRepository userCredentialRepository;
    private final JwtConfigProps jwtConfigProps;

    public String generateToken(String email) {
        Optional<UserCredential> optionalUserCredential = userCredentialRepository.findById(email);
        if (optionalUserCredential.isEmpty()) {
            throw new UsernameNotFoundException("User with email " + email + " not found");
        }
        UserCredential userCredential = optionalUserCredential.get();
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", userCredential.getEmail());
        claims.put("role", userCredential.getRole());
        return generateToken(claims, userCredential);
    }

    private String generateToken(Map<String, Object> claims, UserCredential userCredential) {
        return Jwts
                .builder()
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + Duration.ofMinutes(30).toMillis()))
                .signWith(getSecretKey())
                .compact();
    }

    private SecretKey getSecretKey() {
        byte[] encodedKey = Decoders.BASE64.decode(jwtConfigProps.getSecret());
        return Keys.hmacShaKeyFor(encodedKey);
    }
}
