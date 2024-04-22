package org.example;

import static java.lang.Math.random;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        //initialise a clientRateLimit manager
        ClientRateLimitManager clientRateLimitManager = ClientRateLimitManager.getInstance();

        // adding request clients
        clientRateLimitManager.addClient("client-1", RateLimiterType.SLIDING_WINDOW, 3, 2000L);
        clientRateLimitManager.addClient("client-2", RateLimiterType.SLIDING_WINDOW, 4, 3000L);

        for(int i=0;i<1000;i++){
            Thread thread =  new Thread(() -> {
                try {
                    long sleepTime = (long) (1000000L*random());
                    System.out.printf("Thread: %s sleep time: %d\n", Thread.currentThread().getName(), sleepTime);
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Request request = new Request("client-1", System.currentTimeMillis());
                if(clientRateLimitManager.allowRequest(request)){
                    System.out.printf("Thread: %s Request: %s allowed\n", Thread.currentThread().getName(), request);
                }
                else
                    System.err.printf("Thread: %s Request: %s not allowed\n", Thread.currentThread().getName() ,request);
            });
            thread.start();
        }
    }
}