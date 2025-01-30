package org.example.model;

public class Board {
    private Box[][] boxes;

    public Board(int size) {
        boxes = new Box[size][size];
        resetBoard(size);
        printBoard();
    }

    private void resetBoard(int size) {
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                this.boxes[i][j] = new Box(i, j);
            }
        }
    }

    public void updateBoard(int x, int y, Symbol symbol) {
        Box box = this.boxes[x][y];
        box.setSymbol(symbol);
        this.boxes[x][y] = box;
    }

    public Box[][] getBoard() {
        return this.boxes;
    }

    public int getSize() {
        return this.boxes.length;
    }

    public void printBoard() {
        for(int i = 0; i < getSize(); i++) {
            for(int j = 0; j < getSize(); j++) {
                String str = "";
                switch (boxes[i][j].getSymbol()) {
                    case CROSS:
                        str = "X";
                        break;
                    case ZERO:
                        str = "0";
                        break;
                    default:
                        str = "-";
                        break;
                }
                System.out.print(str + "  ");
            }
            System.out.println();
        }
    }
}
