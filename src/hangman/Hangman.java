package hangman;

import java.util.Scanner;
import java.io.Console;

/* This is how you print the word without displaying in the console
if (console == null) {
            System.out.println("No console available");
            return;
        }

        char[] passwordArray = console.readPassword("Enter your password: ");
        String password = new String(passwordArray);

*/

public class Hangman{
 
    public Hangman(){
        start();
    }

    public void start(){
        // Create player1
        // Player player1 = new Player();

        Scanner sc = new Scanner(System.in);
        System.out.println("Choose the WORD to be found"); 
        
        // Must be all leters, can't have numbers or special characters - TBD
        // Must be written but cannot be seen in console- TBD 
        String word = sc.next();

        int wordLenght = word.length();
        System.out.println("_ ".repeat(wordLenght));
        
        // Set the number of lives
        int lives = 10;

        // Iterate over the number of lives
        for (int i = 0; i < lives; i++){
            // Player2 name
            System.out.println("Enter a letter: ");
            // Iterate over the number of chars and check if the guess is the same to any word of the charWord list.
            for (int j = 0; j < wordLenght; j++){
                char guess = sc.next().charAt(0);
                if(containsChar(word, guess)){
                    System.out.println("You guessed right!\n");
                    // Print in the right spot of the word - TBD 
                    System.out.println("_ ".repeat(wordLenght));
                    continue;
                }
                else{
                    System.out.println("Try again");
                    // Try another guess before entering next iteration of loop
                    guess = sc.next().charAt(0);
                }

            }
        }

        sc.close();
    }

    public boolean containsChar(String s, char search) {
        if (s.length() == 0)
            return false;
        else
            // Recursividade
            return s.charAt(0) == search || containsChar(s.substring(1), search);
    }

    
}
