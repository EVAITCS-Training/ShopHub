package com.evaitcsmatt.shophub.webserver;

import com.evaitcsmatt.shophub.webserver.dtos.AuthRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class DockerWebserverTests {

    @Container
    @ServiceConnection
    private static final MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:8.0");

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void registerAndLogin_ShouldSucceed() {
        AuthRequest authRequest = new AuthRequest("testuser12@gmail.com", "String123");

        ResponseEntity<String> registeredResponse = restTemplate.postForEntity(
                "/api/v1/auth/register",
                authRequest,
                String.class
        );

        assertThat(registeredResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(registeredResponse.getBody()).isNotNull();

        ResponseEntity<String> loginResponse = restTemplate.postForEntity(
                "/api/v1/auth/login",
                authRequest,
                String.class
        );

        assertThat(loginResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(loginResponse.getBody()).isNotNull();
    }
}
