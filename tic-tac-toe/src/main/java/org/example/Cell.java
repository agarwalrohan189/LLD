package org.example;

public class Cell {
    private Symbol symbol;

    public Cell() {
        this.symbol = null;
    }

    public Cell(Symbol symbol) {
        this.symbol = symbol;
    }

    public Symbol getSymbol(){
        return symbol;
    }

    public String printSymbol(){
        if(symbol==null) return " ";
        return symbol.name();
    }

    public void fillCell(Symbol symbol){
        this.symbol = symbol;
    }
}
