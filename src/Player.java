package src;

public class Player {
    private String name;
    private char symbol;
    private int range;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    // For the TicTacToe game
    public char getSymbol() {
        return symbol; 
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol; 
    }

    // For the Guess game
    public void setRange(int range) {
        this.range = range; 
    }

    public int getRange() {
        return range;
    }
}
