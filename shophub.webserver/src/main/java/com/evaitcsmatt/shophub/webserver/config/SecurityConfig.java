package com.evaitcsmatt.shophub.webserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .cors(cors -> cors.disable())
                .authorizeHttpRequests(requests ->
                        requests
                                .requestMatchers(
                                        "/api/v1/product/**",
                                        "/api/v1/auth/register",
                                        "/",
                                        "/css/**",
                                        "/js/**"
                                )
                                .permitAll()
                                .requestMatchers("/api/v1/order/")
                                .authenticated()
                                .anyRequest()
                                .hasRole("ADMIN"))

                .formLogin(form -> form
                        .defaultSuccessUrl("/store")
                        .permitAll());
        return http.build();
    }
}
