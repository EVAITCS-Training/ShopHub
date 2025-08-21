package com.evaitcsmatt.shophub.webserver.dtos;

public record AuthRequest(
        String email,
        String password
) {
    public AuthRequest() {
        this("", "");
    }
}
