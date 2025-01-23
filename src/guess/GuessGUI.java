package src.guess;

import src.Player;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GuessGUI {
    private JFrame window;
    private JPanel panel, lowPanel, gamePanel;
    private JTextField guessField;
    private JButton submitButton, startButton, restartButton, exitButton;
    private JLabel welcomeLabel, triesLabel, difficultyLabel, correctionLabel;
    private JComboBox<String> difficultyBox;
    private int range, difficulty, guess;
    private JTextArea historyArea;
    private JScrollPane scrollPane;
    private GuessGame game;
    private Player player;

    private final Color panelBackgroundColor = new Color(224, 224, 224);
    
    public GuessGUI(GuessGame game) {
        this.game = game;
    	//configura frame
    	window = new JFrame("Guess the Number");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(1000, 500);
        window.setLocationRelativeTo(null);

        // Cria o painel principal
        panel = new JPanel();
        window.add(panel, BorderLayout.CENTER);

        // Configura o painel inicial
        initialize();

        // Torna a janela visível
        window.setVisible(true);
    }

    private void initialize() {
         
         //Panel
         panel.setLayout(new GridLayout(4,1,2,2));
         panel.setBackground(panelBackgroundColor);
         
         //welcomelabel
         welcomeLabel = new JLabel("Bem-vindo ao Guess the Number!");
         welcomeLabel.setFont(new Font("Arial", Font.BOLD, 42));
         
         //difficultylabel
         difficultyLabel = new JLabel("Escolha uma dificuldade: ");
         difficultyLabel.setFont(new Font("Arial", Font.PLAIN, 28));
         
         //difficulties
         String[] difficulties = {"1: Fácil", "2: Médio", "3: Difícil", "4: Impossível"};
         difficultyBox = new JComboBox<>(difficulties);
         difficultyBox.setFont(new Font("Arial", Font.PLAIN, 18));
         
         //startbutton
         startButton = new JButton("Iniciar");
         startButton.setFont(new Font("Arial", Font.BOLD, 18));
         startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				chooseDifficulty();
			}
         });
         
         //add everything to panel
         panel.add(welcomeLabel);
         panel.add(difficultyLabel);
         panel.add(difficultyBox);
         panel.add(startButton);
         }

 // public GuessGUI(GuessGame game) {
 //     this.game = game;
 //     setUpGUI();
 // }

 // private void setUpGUI() {
 //     // Main window setup
 //     window = new JFrame("Guess the Number");
 //     window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 //     window.setSize(1000, 500);
 //     window.setLocationRelativeTo(null);

 //     panel = new JPanel();
 //     panel.setLayout(new GridLayout(4, 1, 2, 2));
 //     panel.setBackground(panelBackgroundColor);
 //     window.add(panel, BorderLayout.CENTER);

 //     // Welcome label
 //     welcomeLabel = new JLabel("Bem-vindo ao Guess the Number!");
 //     welcomeLabel.setFont(new Font("Arial", Font.BOLD, 42));

 //     // Difficulty selection
 //     difficultyLabel = new JLabel("Escolha uma dificuldade:");
 //     difficultyLabel.setFont(new Font("Arial", Font.PLAIN, 28));

 //     String[] difficulties = {"1: Fácil", "2: Médio", "3: Difícil", "4: Impossível"};
 //     difficultyBox = new JComboBox<>(difficulties);
 //     difficultyBox.setFont(new Font("Arial", Font.PLAIN, 18));

 //     // Start button
 //     startButton = new JButton("Iniciar");
 //     startButton.setFont(new Font("Arial", Font.BOLD, 18));
 //     startButton.addActionListener(e -> {
 //         chooseDifficulty();
 //         setupGamePanel();
 //     });

 //     // Adding components to the main panel
 //     panel.add(welcomeLabel);
 //     panel.add(difficultyLabel);
 //     panel.add(difficultyBox);
 //     panel.add(startButton);

 //     // Finalize setup
 //     window.setVisible(true);
 // }

    private void chooseDifficulty() {
        difficulty = difficultyBox.getSelectedIndex();
        switch (difficulty) {
            case 0 -> range = 50;
            case 1 -> range = 100;
            case 2 -> range = 500;
            case 3 -> range = 1000;
            default -> range = 100;
        }
        game = new GuessGame(game.getCurrentPlayer(), range);
        setupGamePanel();
    }

    private void setupGamePanel() {
        panel.removeAll();
        panel.setLayout(new BorderLayout(2, 2));

        // History area
        historyArea = new JTextArea(5, 20);
        historyArea.setEditable(false);
        historyArea.setFont(new Font("Arial", Font.PLAIN, 22));
        scrollPane = new JScrollPane(historyArea);
        panel.add(scrollPane, BorderLayout.EAST);

        // Labels and input
        welcomeLabel.setText("Bem vindo, " + game.getCurrentPlayer().getName() + ".");
        correctionLabel = new JLabel("Adivinha o número entre 1 e " + game.getCurrentPlayer().getRange() + ".");
        correctionLabel.setFont(new Font("Arial", Font.PLAIN, 26));
        triesLabel = new JLabel("Tentativas: " + game.getTries());
        triesLabel.setFont(new Font("Arial", Font.PLAIN, 24));

        guessField = new JTextField();
        guessField.setFont(new Font("Arial", Font.PLAIN, 24));
        guessField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    checkGuess();
                }
            }
        });

        // Submit button
        submitButton = new JButton("Enviar palpite.");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 18));
        submitButton.addActionListener(e -> checkGuess());

        // Restart button
        restartButton = new JButton("Reiniciar");
        restartButton.setFont(new Font("Arial", Font.PLAIN, 18));
        restartButton.addActionListener(e -> {
            panel.removeAll();
            initialize();
        });

        // Bottom panel
        lowPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lowPanel.add(restartButton);
        panel.add(lowPanel, BorderLayout.SOUTH);

        // Game panel
        gamePanel = new JPanel(new GridLayout(5, 1, 2, 2));
        gamePanel.add(welcomeLabel);
        gamePanel.add(correctionLabel);
        gamePanel.add(guessField);
        gamePanel.add(submitButton);
        gamePanel.add(triesLabel);
        panel.add(gamePanel, BorderLayout.CENTER);

        panel.revalidate();
        panel.repaint();
    }

    private void checkGuess() {
        try {
            guess = Integer.parseInt(guessField.getText().trim());
            if (guess < 1 || guess > game.getCurrentPlayer().getRange()) {
                correctionLabel.setText("Por favor, insira um número entre 1 e " + game.getCurrentPlayer().getRange() + ".");
                correctionLabel.setForeground(Color.RED);
                guessField.setText("");
                return;
            }

            game.incrementTries();
            triesLabel.setText("Tentativas: " + game.getTries());
            historyArea.append("Tentativa " + game.getTries() + ": " + guess + "\n");

            if (game.checkGuess(guess)) {
                victoryPanel();
            } else if (game.isHigher(guess)) {
                correctionLabel.setText("Errado! O número correto é maior que " + guess + ".");
                correctionLabel.setForeground(Color.ORANGE);
            } else {
                correctionLabel.setText("Errado! O número correto é menor que " + guess + ".");
                correctionLabel.setForeground(Color.ORANGE);
            }
            guessField.setText("");
        } catch (NumberFormatException e) {
            correctionLabel.setText("Por favor, insira um número válido.");
            correctionLabel.setForeground(Color.RED);
        }
    }

    private void victoryPanel() {
        panel.removeAll();
        panel.setLayout(new GridLayout(3, 1, 2, 2));

        JLabel winMessage = new JLabel("Parabéns, acertaste!");
        winMessage.setFont(new Font("Arial", Font.BOLD, 36));
        winMessage.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel numberMessage = new JLabel("O número correto era " + game.getRandomNumber() + ".");
        numberMessage.setFont(new Font("Arial", Font.PLAIN, 28));
        numberMessage.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        restartButton = new JButton("Reiniciar");
        restartButton.setFont(new Font("Arial", Font.PLAIN, 18));
        restartButton.addActionListener(e -> {
            panel.removeAll();
            initialize();
        });

        exitButton = new JButton("Sair");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 18));
        exitButton.addActionListener(e -> window.dispose());

        buttonPanel.add(restartButton);
        buttonPanel.add(exitButton);

        panel.add(winMessage);
        panel.add(numberMessage);
        panel.add(buttonPanel);

        panel.revalidate();
        panel.repaint();
    }
}

