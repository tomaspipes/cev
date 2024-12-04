package src.guess;

import java.util.Scanner; //tomás

public class Validation {
	public int validateDifficulty(int difficulty, Scanner scan) { //validar a dificuldade escolhida
		boolean difficultyValidated = false;
		int[] possibleDifficulties = {1, 2, 3, 4, 5};

		while (!difficultyValidated) {

			for (int x : possibleDifficulties) {
				if (x == difficulty) {
					difficultyValidated = true;
					return difficulty;
				}
			}
			System.out.println("Dificuldade inválida, escolhe entre 1(Fácil), 100(Médio), 500(Difícil) ou 1000(Impossível).");
			difficulty = scan.nextInt();
		}
		return difficulty;

	}
}
