package org.example.model;

public class RateLimiterFactory {
    public static RateLimiter createRateLimiter(String name, long windowTime) {
        switch (name.toLowerCase()) {
            case "fixed":
                return new FixedWindowRateLimiter(windowTime);
            case "sliding":
                return new SlidingWindowRateLimiter(windowTime);
            default:
                throw  new IllegalArgumentException("Unknown Rate Limiter found!");
        }
    }
}
