package hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Random;

public class DifficultyWordChosing {
    
	// Word lists for each difficulty
    private String[] easyWords = {
            "Cao", "Gato", "Sol", "Lua", "Casa", "Carro", "Livro", "Bola", "Peixe", "Arvore",
            "Agua", "Rua", "Maca", "Escola", "Amigo", "Festa", "Pao", "Chave", "Bebe", "Rato", "Pato"
    };

    private String[] mediumWords = {
            "Biblioteca", "Cadeira", "Viagem", "Janela", "Medico", "Jardim", "Montanha", "Lapis",
            "Sapatos", "Professor", "Relogio", "Filme", "Imagem", "Hospital", "Floresta", "Planeta",
            "Oceano", "Ciencia", "Pirata", "Circo"
    };

    private String[] hardWords = {
            "Questionario", "Arqueologia", "Pneumonia", "Refrigerador", "Enciclopedia", "Travesso",
            "Hipopotamo", "Excentrico", "Subterraneo", "Empreendedor", "Paralelepipedo", "Concentracao",
            "Pterodactilo", "Abacaxi", "Otorrinolaringologista", "Subjetividade", "Psicopedagogo",
            "Inconstitucional", "Esquizofrenia", "Subjetividade"
    };

    private String[] impossibleWords = {
            "Pneumoultramicroscopicossilicovulcanoconiotico", "Hipopotomonstrosesquipedaliofobia",
            "Anticonstitucionalissimamente", "Inconstitucionalissimamente", "Otorrinolaringologista",
            "Superextraordinarissimamente", "Paraclorobenzilpirrolidinilhidroxipirimidinol",
            "Hepaticocelularcarcinoma", "Hexafluorossilicofluoreto", "Desoxirribonucleico",
            "Eletromagneticamente", "Radioimunoeletroforese", "Demotécnicoentomofobista",
            "Disprosodicopeptídico", "Hipercorticismo", "Esquizofreniforme", "Clorofluorocarboneto",
            "Polirribonucleotídeo", "Pseudopseudohipoparatireoidismo", "Metodologicamente"
    };

    private String chosenWord; // The selected word
    private String difficulty; // Selected difficulty level

    // Constructor initializes chosenWord to null
    public DifficultyWordChosing() {
        this.chosenWord = null;
    }

    // Method to choose a random word from a word list
    private String getRandomWord(String[] wordList) {
        Random random = new Random();
        return wordList[random.nextInt(wordList.length)];
    }

    // Panel to select difficulty
    public void setupDifficultyPanel(JPanel gamesPanel, Runnable onDifficultySelected) {
        // Clear the previous panel layout
        gamesPanel.removeAll();
        gamesPanel.setLayout(new BorderLayout());

        // Create buttons for difficulty levels
        JPanel difficultyPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        JButton easyButton = new JButton("Fácil");
        JButton mediumButton = new JButton("Médio");
        JButton hardButton = new JButton("Difícil");
        JButton impossibleButton = new JButton("Impossível");

        // Add buttons to panel
        difficultyPanel.add(easyButton);
        difficultyPanel.add(mediumButton);
        difficultyPanel.add(hardButton);
        difficultyPanel.add(impossibleButton);

        // Add an "Instructions" button at the bottom-right corner
        JButton instructionsButton = new JButton("Instruções");
        JPanel instructionsPanel = new JPanel(new BorderLayout());
        instructionsPanel.add(instructionsButton, BorderLayout.EAST);

        gamesPanel.add(difficultyPanel, BorderLayout.CENTER);
        gamesPanel.add(instructionsPanel, BorderLayout.SOUTH);

        // Set action listeners for each difficulty button
        ActionListener difficultyListener = e -> {
            JButton source = (JButton) e.getSource();
            if (source == easyButton) {
                difficulty = "1";
                chosenWord = getRandomWord(easyWords);
            } else if (source == mediumButton) {
                difficulty = "2";
                chosenWord = getRandomWord(mediumWords);
            } else if (source == hardButton) {
                difficulty = "3";
                chosenWord = getRandomWord(hardWords);
            } else if (source == impossibleButton) {
                difficulty = "4";
                chosenWord = getRandomWord(impossibleWords);
            }

            JOptionPane.showMessageDialog(gamesPanel,
                    "Dificuldade selecionada: " + source.getText() + "\nBoa sorte!");
            onDifficultySelected.run(); // Proceed to the next screen
        };

        easyButton.addActionListener(difficultyListener);
        mediumButton.addActionListener(difficultyListener);
        hardButton.addActionListener(difficultyListener);
        impossibleButton.addActionListener(difficultyListener);

        // Action listener for instructions
        instructionsButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(gamesPanel,
                    "Escolha uma dificuldade:\n" +
                            "1. Fácil - Palavras simples.\n" +
                            "2. Médio - Palavras moderadas.\n" +
                            "3. Difícil - Palavras desafiadoras.\n" +
                            "4. Impossível - Apenas para corajosos!",
                    "Instruções", JOptionPane.INFORMATION_MESSAGE);
        });

        // Refresh the panel
        gamesPanel.revalidate();
        gamesPanel.repaint();
    }

    // Getters for chosen word and difficulty
    public String getChosenWord() {
        return chosenWord;
    }

    public String getDifficulty() {
        return difficulty;
    }
}