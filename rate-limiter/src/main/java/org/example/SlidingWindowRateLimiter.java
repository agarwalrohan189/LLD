package org.example;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.SynchronousQueue;

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
    public boolean allowRequest(Request request) {
//        System.out.println("Thread: " + Thread.currentThread().getName() + " Request: "+request + " queue size: " + slidingWindow.size());
        updateQueue(request.getTimestamp());
//        System.out.println("Thread: " + Thread.currentThread().getName() + " Request: "+request + " queue size: " + slidingWindow.size());
        if(slidingWindow.size()<numRequests){
            slidingWindow.offer(request.getTimestamp());
            return true;
        }
        return false;
    }

    private void updateQueue(long timestamp){
//        System.out.println("---------ENTER QUEUE---------");
//        System.out.printf("Timestamp: %d\n", timestamp);
//        for(long t: slidingWindow){
//            System.out.printf(" %d ", t);
//        }
//        System.out.println();
        while(!slidingWindow.isEmpty() && slidingWindow.peek()<timestamp-timeWindow){
            slidingWindow.poll();
        }
//        System.out.println("---------EXIT QUEUE---------");
//        System.out.printf("Timestamp: %d\n", timestamp);
//        for(long t: slidingWindow){
//            System.out.printf(" %d ", t);
//        }
//        System.out.println();
    }
}
