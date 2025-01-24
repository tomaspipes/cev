package src.guess;

import java.util.Random;

public class GuessGame {
    private int randomNumber;
    private int tries;
    private int range;
    private Random rand;

    public GuessGame(int range) {
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

    public int getRandomNumber() {
        return randomNumber;
    }

    public void resetGame() {
        randomNumber = rand.nextInt(range) + 1;
        tries = 0;
    }
}
