package src;

import src.guess.Guess;
import src.hangman.Hangman;
import src.ticTacToe.TicTacToeGame;
import src.ticTacToe.PlayerTicTacToe;

public class GameFactory{
    
    public Game getGame(String gameType){

        if (gameType.equalsIgnoreCase("GUESS")){
            return new Guess();
        }
        else if(gameType.equalsIgnoreCase("HANGMAN")){
            return new Hangman();
        }
        else if(gameType.equalsIgnoreCase("TICTACTOE")){
            PlayerTicTacToe player1 = new PlayerTicTacToe("Tomás", 'X');
            PlayerTicTacToe player2 = new PlayerTicTacToe("Silvia", 'O');
            return new TicTacToeGame(player1, player2);
        }else {
            throw new IllegalArgumentException("Please choose between Guess, Hangman OR TicTacToe");
        }
    }
}
