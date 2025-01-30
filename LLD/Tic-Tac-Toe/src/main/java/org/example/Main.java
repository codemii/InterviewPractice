package org.example;

import org.example.controller.GameController;
import org.example.model.Player;
import org.example.model.Symbol;

import java.util.Random;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Opt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.

        GameController gameController = GameController.getInstance();

        //create player
        Player player1 = new Player("Alice", "P1");
        Player player2 = new Player("Bob", "P2");

        //randomly assign CROSS or ZERO to players
        Random random = new Random();
        int num = random.nextInt(2);
        System.out.println("Random number is : " + num);
        switch (num) {
            case 0:
                player1.setSymbol(Symbol.CROSS);
                player2.setSymbol(Symbol.ZERO);
                break;
            default:
                player1.setSymbol(Symbol.ZERO);
                player2.setSymbol(Symbol.CROSS);
                break;
        }

        gameController.startGame(player1, player2);
    }
}