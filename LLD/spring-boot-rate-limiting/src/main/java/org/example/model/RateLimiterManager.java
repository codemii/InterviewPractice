package org.example.model;

import java.util.HashMap;
import java.util.Map;

public class RateLimiterManager {
    private static RateLimiterManager instance = null;
    private static Map<String, RateLimiter> rateLimiterMap;
    private RateLimiterManager() {
        rateLimiterMap = new HashMap<>();
    }

    public static synchronized RateLimiterManager getInstance(String name, long windowTime) {
        if(instance == null) {
            instance = new RateLimiterManager();
        }

        if(rateLimiterMap.get(name) == null) {
            RateLimiter rateLimiter1 = RateLimiterFactory.createRateLimiter(name, windowTime);
            rateLimiterMap.put(name, rateLimiter1);
        }

        return instance;
    }

    public boolean isAllowRequest(String rateLimiterName, int allowedTPS, String apiName) {
        return rateLimiterMap.get(rateLimiterName).isRequestAllowed(allowedTPS, apiName);
    }
}
