package com.fram.ecommerce.utils;

import java.time.LocalDateTime;

public class ResponseHelper {
    private final boolean status;
    private final String message;

    public ResponseHelper(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return LocalDateTime.now().toString();
    }
}
