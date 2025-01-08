package org.example.model;

import java.util.HashMap;
import java.util.Map;

public class FixedWindowRateLimiter implements RateLimiter{
    private long maxWindowSize;
    private Map<String, Long> startTimeMapForAPI;
    private Map<String, Integer> requestCountMap;
    public FixedWindowRateLimiter(long windowTime) {
        this.maxWindowSize = windowTime;
        this.startTimeMapForAPI = new HashMap<>();
        this.requestCountMap = new HashMap<>();
    }

    @Override
    public boolean isRequestAllowed(int maxAllowedTPS, String apiName) {
        long currentTime = System.currentTimeMillis();

        startTimeMapForAPI.putIfAbsent(apiName, currentTime);
        long startTime = startTimeMapForAPI.get(apiName);

        if(currentTime - startTime >= maxWindowSize) {
            startTimeMapForAPI.put(apiName, currentTime);
            requestCountMap.put(apiName, 1);
            return true;
        }

        int requestCount = requestCountMap.getOrDefault(apiName, 0);
        if(requestCount < maxAllowedTPS) {
            requestCountMap.put(apiName, ++requestCount);
            return true;
        }
        return false;
    }
}
