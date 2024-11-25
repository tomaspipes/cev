package src;

public class GameFactory{
    
    public Game getGame(String gameType){

        if (gameType.equalsIgnoreCase("GUESS")){
            return new Guess();
        }
        else if(gameType.equalsIgnoreCase("GAME2")){
            return new Game2();
        }
        else if(gameType.equalsIgnoreCase("GAME3")){
            return new Game3();
        }else {
            throw new IllegalArgumentException("Please choose between Guess, Game2 OR Game3");
        }
    }
}
