package org.example.model;

public interface RateLimiter {
    boolean isRequestAllowed(int allowedTPS, String apiName);
}
