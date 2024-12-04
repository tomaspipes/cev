package src.guess;

public class Difficulty {
	private int range;

	public Difficulty(int range) {
			this.range = range;
			}

	public int getRange() {
	
		return range;
	}
	public int giveRange(int dificuldade) {//atribuir uma range a cada dificuldade
		switch(dificuldade) {
		case 1: return 50;
		case 2: return 100;
		case 3: return 500;
		case 4: return 1000;
		default: 
            throw new IllegalArgumentException("Dificuldade inválida: " + dificuldade);
}
}
}
