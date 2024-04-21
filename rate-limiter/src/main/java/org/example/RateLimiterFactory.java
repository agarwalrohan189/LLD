package org.example;

public class RateLimiterFactory {

    public static RateLimiter getRateLimiter(RateLimiterType type, long numRequests, long timeWindow){
        switch (type){
            case SLIDING_WINDOW -> {
                return new SlidingWindowRateLimiter(numRequests, timeWindow);
            }
            case LEAKY_BUCKET -> {
                return new LeakyBucketRateLimiter(numRequests, timeWindow);
            }
        }
        throw new IllegalArgumentException("Invalid rate limiter type");
    }
}
