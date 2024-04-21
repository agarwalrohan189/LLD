package org.example;

public interface RateLimiter {

    boolean allowRequest(Request request);
}
