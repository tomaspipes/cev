package src.guess;

import java.util.Random;
import java.util.Scanner;

import src.Game;

public class Guess implements Game{
 
    public Guess(){
        start();
    }

    @Override
    public void start(){
    	Scanner scan = new Scanner(System.in);

    	System.out.println("Qual é o nome do Player?");
    	String name = scan.next();
    	Player player1 = new Player(name); //definir player 1 e atribuir nome, variável name
    	System.out.println("Bem-vindo " + player1.getName() + "!");
    	
    	System.out.println("Qual é a dificuldade?? \nDificuldade 1 (Fácil) -> 1 - 50\nDificuldade 2 (Médio) -> 1 - 100\nDificuldade 3 (Difícil) -> 1 - 500 \nDificuldade 3 (Impossível) -> 1 - 1000");
    	int choiceDifficulty = scan.nextInt();
    	Validation validate = new Validation();//variável do tipo validation
    	int difficulty = validate.validateDifficulty(choiceDifficulty,scan);//método para validar
    	Difficulty dific = new Difficulty(difficulty);//variável do tipo difficulty
    	int range = dific.giveRange(difficulty);//atribui uma range à dificuldade escolhida
    	
    	Random rand = new Random();
    	int randomNumber = rand.nextInt(range) + 1;//gerar número escolhido dentro da range
    	System.out.println(randomNumber);
    	int tries = 0;
    	boolean continueGame = true;
    	
    	while(continueGame) {
    	System.out.println("Adivinha o número:");
    	int playerGuess = scan.nextInt();
    	tries++;//contador de tentativas
    		
    		
    	if(playerGuess == randomNumber) {
    		System.out.println("Correto! Acertaste o número.\nPrecisaste de " + tries + " tentativas."); //vitória
    		break;
    	} 
    	else if(playerGuess > randomNumber){
    		System.out.println("Errado! O número correto é menor.");
    	}
    	else {
    		System.out.println("Errado! O número correto é maior.");
    		}
    	}
    	scan.close();
    }

}
