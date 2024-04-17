package org.example;

import java.util.UUID;

public class Move {
    private String id;
    private String playerId;
    private int row;
    private int col;
    private Symbol symbol;

    public Move(String playerId, int row, int col, Symbol symbol) {
        this.id = UUID.randomUUID().toString();
        this.playerId = playerId;
        this.row = row;
        this.col = col;
        this.symbol = symbol;
    }

    public String getId() {
        return id;
    }

    public String getPlayerId() {
        return playerId;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Symbol getSymbol() {
        return symbol;
    }
}
