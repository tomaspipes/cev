package src.ticTacToe;

import src.Player;

public class PlayerTicTacToe extends Player {
    private char symbol;

    public PlayerTicTacToe(String name, char symbol) {
        super(name);
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol; 
    }
}
