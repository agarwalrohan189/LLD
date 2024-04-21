package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        //initialise a clientRateLimit manager
        ClientRateLimitManager clientRateLimitManager = ClientRateLimitManager.getInstance();

        // adding request clients
        clientRateLimitManager.addClient("client-1", RateLimiterType.SLIDING_WINDOW, 3, 2000L);
        clientRateLimitManager.addClient("client-2", RateLimiterType.SLIDING_WINDOW, 4, 3000L);

        for(int i=0;i<10;i++){
            Request request = new Request("client-1", System.currentTimeMillis());
            if(clientRateLimitManager.allowRequest(request)){
                System.out.printf("Request: %s allowed\n",request);
            }
            else
                System.err.printf("Request: %s not allowed\n",request);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}