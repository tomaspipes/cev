package src.guess;

import src.Player;

public class PlayerGuess extends Player{
    private String name;
   // private char symbol;

    public PlayerGuess(String name) { 
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
 }
