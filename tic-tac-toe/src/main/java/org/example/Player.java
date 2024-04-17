package org.example;

public class Player {
    private String id;
    private String name;
    private Symbol allottedSymbol;

    public Player(String id, String name, Symbol allottedSymbol) {
        this.id = id;
        this.name = name;
        this.allottedSymbol = allottedSymbol;
    }

    public String getId(){ return id; }

    public String getName(){ return name; }

    public Symbol getSymbol(){
        return allottedSymbol;
    }
}
