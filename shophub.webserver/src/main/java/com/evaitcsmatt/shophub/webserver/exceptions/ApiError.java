package com.evaitcsmatt.shophub.webserver.exceptions;

import java.time.LocalDateTime;

public record ApiError(
        String message,
        int status,
        String endpoint,
        LocalDateTime timestamp
) {
}
