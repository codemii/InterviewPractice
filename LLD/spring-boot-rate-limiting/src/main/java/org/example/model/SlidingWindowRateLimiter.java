package org.example.model;

public class SlidingWindowRateLimiter implements RateLimiter {
    private int maxAllowedTPS;
    private long maxWindowSize;
    public SlidingWindowRateLimiter(long windowTime) {
        this.maxWindowSize = windowTime;
    }

    public boolean isRequestAllowed(int allowedTPS, String apiName) {
        this.maxAllowedTPS = allowedTPS;
        return false;
    }
}
