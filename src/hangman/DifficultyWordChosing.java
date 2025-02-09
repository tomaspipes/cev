package src.hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Random;

public class DifficultyWordChosing {

    // Construtor da class DifficultyWordChosing, inicia a chosenWord a null
    public DifficultyWordChosing() {
        this.chosenWord = null;
    }
    
	// Lista de palavras possiveis para adivinhar
    
    //Palavras para a dificuldade Fácil
    private String[] easyWords = {
            "Cao", "Gato", "Sol", "Lua", "Casa", "Carro", "Livro", "Bola", "Peixe", "Arvore",
            "Agua", "Rua", "Maca", "Escola", "Amigo", "Festa", "Pao", "Chave", "Bebe", "Rato", "Pato"
    };

  //Palavras para a dificuldade Média
    private String[] mediumWords = {
            "Biblioteca", "Cadeira", "Viagem", "Janela", "Medico", "Jardim", "Montanha", "Lapis",
            "Sapatos", "Professor", "Relogio", "Filme", "Imagem", "Hospital", "Floresta", "Planeta",
            "Oceano", "Ciencia", "Pirata", "Circo"
    };

  //Palavras para a dificuldade Dificil
    private String[] hardWords = {
            "Questionario", "Arqueologia", "Pneumonia", "Refrigerador", "Enciclopedia", "Travesso",
            "Hipopotamo", "Excentrico", "Subterraneo", "Empreendedor", "Paralelepipedo", "Concentracao",
            "Pterodactilo", "Abacaxi", "Otorrinolaringologista", "Subjetividade", "Psicopedagogo",
            "Inconstitucional", "Esquizofrenia", "Subjetividade"
    };

  //Palavras para a dificuldade Impossivel
    private String[] impossibleWords = {
            "Pneumoultramicroscopicossilicovulcanoconiotico", "Hipopotomonstrosesquipedaliofobia",
            "Anticonstitucionalissimamente", "Inconstitucionalissimamente", "Otorrinolaringologista",
            "Superextraordinarissimamente", "Paraclorobenzilpirrolidinilhidroxipirimidinol",
            "Hepaticocelularcarcinoma", "Hexafluorossilicofluoreto", "Desoxirribonucleico",
            "Eletromagneticamente", "Radioimunoeletroforese", "Demotécnicoentomofobista",
            "Disprosodicopeptídico", "Hipercorticismo", "Esquizofreniforme", "Clorofluorocarboneto",
            "Polirribonucleotídeo", "Pseudopseudohipoparatireoidismo", "Metodologicamente"
    };

    
    private String chosenWord; // String para a palavra escolhida de forma aleatória
    private String difficulty; // String para a dificuldade escolhida

    
    public void getRandomWord(String difficulty) {
        Random random = new Random();
        
     // Switch Case para determinar a dificuldade e em que array escolher a palavra aleatória
        switch(difficulty){
            
        	// Seleciona uma palavra do array easyWords e atribui á variável chosenWord
        	case "Fácil" :
            this.chosenWord = easyWords[random.nextInt(easyWords.length)];
            break;

            // Seleciona uma palavra do array mediumWords e atribui á variável chosenWord
            case "Médio" :
            	this.chosenWord = mediumWords[random.nextInt(mediumWords.length)];
            break;

            // Seleciona uma palavra do array hardWords e atribui á variável chosenWord
            case "Difícil" :
            	this.chosenWord = hardWords[random.nextInt(hardWords.length)];
            break;
        
            // Seleciona uma palavra do array impossibleWords e atribui á variável chosenWord
            case "Impossivel" :
            	this.chosenWord = impossibleWords[random.nextInt(impossibleWords.length)];
            break;
        }
    }
    
    
   

    // Getters para a palavra e a dificuldade escolhida
    public String getChosenWord() {
        return chosenWord;
    }

    public String getDifficulty() {
        return difficulty;
    }
}
