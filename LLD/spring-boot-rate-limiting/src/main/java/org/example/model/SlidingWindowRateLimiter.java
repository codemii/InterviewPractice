package org.example.model;

import java.util.*;

public class SlidingWindowRateLimiter implements RateLimiter {
    private long maxWindowSize;
    private Map<String, Queue<Long>> startTimeMapForAPI;
    public SlidingWindowRateLimiter(long windowTime) {
        this.maxWindowSize = windowTime;
        this.startTimeMapForAPI = new HashMap<>();
    }

    public boolean isRequestAllowed(int maxAllowedTPS, String apiName) {
        Long currentTime = System.currentTimeMillis();
        startTimeMapForAPI.putIfAbsent(apiName, new LinkedList<>());
        Queue<Long> requests = startTimeMapForAPI.get(apiName);

        while(!requests.isEmpty() && currentTime - requests.peek() >= maxWindowSize) {
            requests.poll();
        }

        if(requests.size() < maxAllowedTPS) {
            requests.add(currentTime);
            startTimeMapForAPI.put(apiName, requests);
            return true;
        }

        return false;
    }
}
