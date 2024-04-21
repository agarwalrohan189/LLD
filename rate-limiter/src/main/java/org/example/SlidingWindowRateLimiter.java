package org.example;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SlidingWindowRateLimiter implements RateLimiter{

    private Queue<Long> slidingWindow;
    private long numRequests;
    //time window in miliSeconds
    private long timeWindow;

    public SlidingWindowRateLimiter(long numRequests, long timeWindow) {
        this.numRequests = numRequests;
        this.timeWindow = timeWindow;
        this.slidingWindow = new ConcurrentLinkedQueue<>();
    }

    @Override
    public synchronized boolean allowRequest(Request request) {
        updateQueue(request.getTimestamp());
        if(slidingWindow.size()<numRequests){
            slidingWindow.offer(request.getTimestamp());
            return true;
        }
        return false;
    }

    private void updateQueue(long timestamp){
        while(!slidingWindow.isEmpty() && slidingWindow.peek()<timestamp-timeWindow){
            slidingWindow.poll();
        }
    }
}
