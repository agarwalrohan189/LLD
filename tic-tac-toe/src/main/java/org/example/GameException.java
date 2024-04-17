package org.example;

public class GameException extends Exception{
    private int status;

    public GameException(String message) {
        super(message);
    }

    public GameException(int status, String message) {
        super(message);
        this.status = status;
    }

    public GameException(Exception e){
        super(e);
    }

    public String getMessage(){
        return super.getMessage();
    }
}
