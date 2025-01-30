package org.example.model;

public class Box {
    private Symbol symbol;
    private int x;
    private int y;
    public Box(int i, int j) {
        this.symbol = Symbol.NONE;
        this.x = i;
        this.y = j;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
