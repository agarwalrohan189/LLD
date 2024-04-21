package org.example;

public class Request {

    private String clientId;
    private long timestamp;

    public Request(String clientId, long timestamp) {
        this.clientId = clientId;
        this.timestamp = timestamp;
    }

    public String getClientId() {
        return clientId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString(){
        return String.format("Client Id: %s, request time: %d (secs: %d)", clientId, timestamp, timestamp/1000);
    }
}
