package src.guess;

import java.util.Random;
import src.guess.GuessGUI;
import src.Game;
import src.Player;

public class GuessGame implements Game{
	private int randomNumber;
	private int tries;
	private int range;
	private Random rand;
    private GuessGUI gui;
    private Player player;

    public GuessGame(Player player) {
        this.player = player;
        start();
    }

    @Override
    public void start() {
        new GuessGUI(this);
    }

    public void setRange(int range) {
        this.range = range;
    }

	public int getTries() {
		return tries;
	}

    public int incrementTries() {
		tries++;
        return tries;
	}
	
	public int getRange() {
		return range;
	}
	
	public int getRandomNumber() {
		return randomNumber;
	}
	
	public boolean checkGuess(int guess) {
		tries++;
		return guess == randomNumber;
	}
	
	public boolean isHigher(int guess) {
		return guess < randomNumber;
	}
	
	public boolean isLower(int guess) {
		return guess > randomNumber;
	}

	public Player getCurrentPlayer() {
        return player;
    }
	
}
