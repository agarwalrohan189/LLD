package org.example;

public class LeakyBucketRateLimiter implements RateLimiter{

    private long numRequests;
    private long timeWindow;

    public LeakyBucketRateLimiter(long numRequests, long timeWindow) {
        this.numRequests = numRequests;
        this.timeWindow = timeWindow;
    }

    @Override
    public boolean allowRequest(Request request) {
        return false;
    }
}
