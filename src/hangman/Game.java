package hangman;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private JPanel mainPanel;
    private Player player;
    private String selectedWord;
    private char[] currentGuess;
    private List<Character> wrongGuesses;
    private int triesLeft = 10;
    private JLabel triesLabel;
    private JLabel wordLabel;
    private JLabel wrongLettersLabel;
    private HangmanPanel hangmanPanel;

    private JTextField guessField;
    
    public Game(JPanel parentPanel, Player player, String chosenWord) {
        this.mainPanel = parentPanel;
        this.player = player;
        this.selectedWord = chosenWord.toUpperCase();
        this.currentGuess = new char[chosenWord.length()];
        this.wrongGuesses = new ArrayList<>();

        for (int i = 0; i < currentGuess.length; i++) {
            currentGuess[i] = chosenWord.charAt(i) == ' ' ? ' ' : '_';
        }

        setupGameGUI();
    }

    
    private void setupGameGUI() {
        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());

        hangmanPanel = new HangmanPanel();
        hangmanPanel.setPreferredSize(new Dimension(500, 500));

        triesLabel = new JLabel("Tentativas restantes: " + triesLeft);
        triesLabel.setFont(new Font("Arial", Font.BOLD, 16));

        wrongLettersLabel = new JLabel("Letras erradas: ");
        wrongLettersLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.add(triesLabel, BorderLayout.NORTH);
        infoPanel.add(wrongLettersLabel, BorderLayout.SOUTH);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(hangmanPanel, BorderLayout.CENTER);
        topPanel.add(infoPanel, BorderLayout.WEST);

        wordLabel = new JLabel(formatCurrentGuess());
        wordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        wordLabel.setHorizontalAlignment(SwingConstants.CENTER);

        guessField = new JTextField();
        JButton guessButton = new JButton("Adivinhar");
        guessButton.addActionListener(e -> handleGuess(guessField.getText().toUpperCase().trim()));
        guessField.addActionListener(e -> handleGuess(guessField.getText().toUpperCase().trim()));

        JPanel guessPanel = new JPanel(new BorderLayout());
        guessPanel.add(guessField, BorderLayout.CENTER);
        guessPanel.add(guessButton, BorderLayout.EAST);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(wordLabel, BorderLayout.NORTH);
        bottomPanel.add(guessPanel, BorderLayout.SOUTH);

        mainPanel.add(topPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void handleGuess(String input) {
        if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
            JOptionPane.showMessageDialog(mainPanel, "Apenas uma letra válida é permitida!", "Erro", JOptionPane.ERROR_MESSAGE);
            guessField.setText("");
            return;
        }

        char guessedLetter = input.charAt(0);

        if (selectedWord.contains(String.valueOf(guessedLetter))) {
            for (int i = 0; i < selectedWord.length(); i++) {
                if (selectedWord.charAt(i) == guessedLetter) {
                    currentGuess[i] = guessedLetter;
                }
            }
        } else if (!wrongGuesses.contains(guessedLetter)) {
            wrongGuesses.add(guessedLetter);
            triesLeft--;
        }
        
        guessField.setText("");

        updateGameStatus();
    }

    private void updateGameStatus() {
        wordLabel.setText(formatCurrentGuess());
        wrongLettersLabel.setText("Letras erradas: " + wrongGuesses);
        triesLabel.setText("Tentativas restantes: " + triesLeft);
        hangmanPanel.setTriesLeft(triesLeft);

        if (String.valueOf(currentGuess).equals(selectedWord)) {
            JOptionPane.showMessageDialog(mainPanel, "Parabéns, você venceu!", "Vitória", JOptionPane.INFORMATION_MESSAGE);
            returnToMenu();
        } else if (triesLeft == 0) {
            JOptionPane.showMessageDialog(mainPanel, "Game Over! A palavra era: " + selectedWord, "Derrota", JOptionPane.ERROR_MESSAGE);
            returnToMenu();
        }
    }

    private String formatCurrentGuess() {
        StringBuilder formatted = new StringBuilder();
        for (char c : currentGuess) {
            formatted.append(c).append(" ");
        }
        return formatted.toString().trim();
    }

    private void returnToMenu() {
        mainPanel.removeAll();
        new GameGUI().setupMainFrame(); // Chama a tela inicial
    }
    
}