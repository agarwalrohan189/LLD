package org.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClientRateLimitManager {

    private Map<String, RateLimiter> rateLimiterMap;
    private static ClientRateLimitManager clientRateLimitManagerInstance;

    private ClientRateLimitManager(){
        rateLimiterMap = new ConcurrentHashMap<>();
    }

    public static ClientRateLimitManager getInstance(){
        if(clientRateLimitManagerInstance == null){
            synchronized (ClientRateLimitManager.class){
                if(clientRateLimitManagerInstance == null){
                    clientRateLimitManagerInstance = new ClientRateLimitManager();
                }
            }
        }
        return clientRateLimitManagerInstance;
    }

    public void addClient(String clientId, RateLimiterType rateLimiterType, long numRequests, long timeWindow){
        RateLimiter rateLimiter = RateLimiterFactory.getRateLimiter(rateLimiterType, numRequests, timeWindow);
        rateLimiterMap.putIfAbsent(clientId, rateLimiter);
    }

    public void removeClient(String clientId){
        rateLimiterMap.remove(clientId);
    }

    public boolean allowRequest(Request request){
        String clientId = request.getClientId();
        if(clientId == null){
            throw new IllegalArgumentException("No client id present in the request");
        }
        RateLimiter rateLimiter = rateLimiterMap.get(clientId);
        if(rateLimiter == null){
            throw new IllegalStateException(String.format("Client for id: %s not added with rate limiter", clientId));
        }
        return rateLimiter.allowRequest(request);
    }

}
