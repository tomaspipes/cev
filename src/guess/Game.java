package guess;

import java.util.Random;

public class Game {
	private int randomNumber;
	private int tries;
	private int range;
	private Random rand;
	
	public Game(int range) {
		this.range = range;
		rand = new Random();
		randomNumber = rand.nextInt(range) + 1;
		tries = 0;
	}
	
	public int getTries() {
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
	
	
}
