package src;

import src.guess.Guess;
import src.hangman.Hangman;
import src.coinMarket.CoinMarket;

public class GameFactory{
    
    public Game getGame(String gameType){

        if (gameType.equalsIgnoreCase("GUESS")){
            return new Guess();
        }
        else if(gameType.equalsIgnoreCase("HANGMAN")){
            return new Hangman();
        }
        else if(gameType.equalsIgnoreCase("COINMARKET")){
            return new CoinMarket();
        }else {
            throw new IllegalArgumentException("Please choose between Guess, Game2 OR Game3");
        }
    }
}
