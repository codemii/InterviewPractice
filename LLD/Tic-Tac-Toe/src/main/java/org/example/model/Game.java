package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Board gameBoard;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private GameStatus gameStatus;
    private int totalMoves;

    public Game(int boardSize, Player player1, Player player2) {
        this.gameBoard = new Board(boardSize);
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = this.player1.getSymbol() == Symbol.CROSS ? this.player1 : this.player2;
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.totalMoves = 0;
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Player1 : " + player1.getName() + ", Symbol : " + player1.getSymbol());
        System.out.println("Player2 : " + player2.getName() + ", Symbol : " + player2.getSymbol());
        while(this.gameStatus == GameStatus.IN_PROGRESS) {
            System.out.println("Player " + currentPlayer.getName() + "'s turn");
            this.gameBoard.printBoard();
            System.out.print("Enter row (0, 1, or 2): ");
            int x = scanner.nextInt();
            System.out.print("Enter column (0, 1, or 2): ");
            int y = scanner.nextInt();
            makeMove(x, y);
        }
    }

    private void makeMove(int x, int y) {
        if(isValidMove(x, y)) {
            gameBoard.updateBoard(x, y, currentPlayer.getSymbol());
            this.totalMoves++;

            if(isWinOrDraw(x, y)) {
                return;
            }

            //switch player
            switchTurn();
        } else {
            System.out.println("Invalid move, please try again!");
        }
    }

    private boolean isValidMove(int x, int y) {
        int boardSize = gameBoard.getSize();

        if(x >= 0 && y >= 0 && x <= boardSize-1 && y <= boardSize-1) {
            Box box = gameBoard.getBoard()[x][y];
            if(box.getSymbol() == Symbol.NONE) {
                return true;
            }
        }

        return false;
    }

    private boolean isWinOrDraw(int x, int y) {
        if(isWin(x, y)) {
            System.out.println("Congratulation, " + currentPlayer.getName() + " win the game!");
            this.gameStatus = currentPlayer == player1 ? GameStatus.PLAYER1_WIN : GameStatus.PLAYER2_WIN;
            this.gameBoard.printBoard();
            return true;
        }

        if(this.totalMoves == (this.gameBoard.getSize() * this.gameBoard.getSize())) {
            System.out.println("It's a draw, please play again!");
            this.gameStatus = GameStatus.DRAW;
            this.gameBoard.printBoard();
            return true;
        }
        return false;
    }

    private boolean isWin(int x, int y) {
        Box[][] boxes = this.gameBoard.getBoard();
        Symbol currentPlayerSymbol = this.currentPlayer.getSymbol();

        //check row
        int i;
        for(i = 0; i < boxes.length; i++) {
            if(boxes[x][i].getSymbol() != currentPlayerSymbol) {
                break;
            }
        }
        if(i == boxes.length) return true;

        //check column
        for(i = 0; i < boxes.length; i++) {
            if(boxes[i][y].getSymbol() != currentPlayerSymbol) {
                break;
            }
        }
        if(i == boxes.length) return true;

        if(x == y) {
            //check diagonal
            for(i = 0; i < boxes.length; i++) {
                if(boxes[i][i].getSymbol() != currentPlayerSymbol) {
                    break;
                }
            }
            if(i == boxes.length) return true;

            //check anti diagonal
            for(i = boxes.length - 1; i >= 0; i--) {
                if(boxes[i][i].getSymbol() != currentPlayerSymbol) {
                    break;
                }
            }
            if(i == -1) return true;
        }
        return false;
    }

    private void switchTurn() {
        this.currentPlayer = (this.currentPlayer == player1) ? this.player2 : this.player1;
    }
}
