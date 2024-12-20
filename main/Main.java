package main;

import src.GameFactory;
import src.Game;

import java.util.Scanner;

public class Main {
      public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Choose a game: GUESS, HANGMAN, or TICTACTOE");
        String gameType = sc.nextLine();

        GameFactory gameFactory = new GameFactory();
        try {
            Game selectedGame = gameFactory.getGame(gameType);
            selectedGame.start(); // Start the chosen game
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        sc.close();
    }
}
