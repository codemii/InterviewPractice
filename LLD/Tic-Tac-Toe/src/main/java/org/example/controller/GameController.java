package org.example.controller;

import org.example.model.Game;
import org.example.model.Player;

import java.util.Scanner;

public class GameController {
    private static GameController instance = null;

    private GameController() {
    }

    public static synchronized GameController getInstance() {
        if(instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    public void startGame(Player player1, Player player2) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter board size like 3, 4, or 5 : ");
        int size = scanner.nextInt();
        if(size == 3 || size == 4 || size == 5) {
            Game game = new Game(size, player1, player2);
            game.playGame();
        } else {
            System.out.println("you have entered invalid board size");
        }
    }
}
