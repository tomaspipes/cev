package src.guess;

import src.Player;
import src.guess.GuessGame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

    public GuessGUI(Player player) {
        this.player = player;
        setupGui(); // Initialize GUI setup
        initialize(); // Set up the initial panel
        window.setVisible(true); // Show the window
    }

    private void setupGui() {
        // Create the main window
        window = new JFrame("Guess the Number");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(1000, 500);
        window.setLocationRelativeTo(null);

        // Create the main panel and add it to the window
        panel = new JPanel();
        window.add(panel, BorderLayout.CENTER);
    }

    private void initialize() {
        // Configure the initial panel layout
        panel.setLayout(new GridLayout(4, 1, 2, 2));
        panel.setBackground(panelBackgroundColor);

        // Welcome label
        welcomeLabel = new JLabel("Bem-vindo ao Guess the Number!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 42));

        // Difficulty label
        difficultyLabel = new JLabel("Escolha uma dificuldade: ");
        difficultyLabel.setFont(new Font("Arial", Font.PLAIN, 28));

        // Difficulty options
        String[] difficulties = {"1: Fácil", "2: Médio", "3: Difícil", "4: Impossível"};
        difficultyBox = new JComboBox<>(difficulties);
        difficultyBox.setFont(new Font("Arial", Font.PLAIN, 18));

        // Start button
        startButton = new JButton("Iniciar");
        startButton.setFont(new Font("Arial", Font.BOLD, 18));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        // Add components to the panel
        panel.add(welcomeLabel);
        panel.add(difficultyLabel);
        panel.add(difficultyBox);
        panel.add(startButton);
    }

    private void startGame() {
        difficulty = difficultyBox.getSelectedIndex();
        switch (difficulty) {
            case 0 -> range = 50;
            case 1 -> range = 100;
            case 2 -> range = 500;
            case 3 -> range = 1000;
            default -> range = 100;
        }

        game = new GuessGame(range);
        setupGamePanel();
    }

    private void setupGamePanel() {
        // Clear the panel and set new layout
        panel.removeAll();
        panel.setLayout(new BorderLayout(2, 2));

        // History area
        historyArea = new JTextArea(5, 20);
        historyArea.setEditable(false);
        historyArea.setFont(new Font("Arial", Font.PLAIN, 22));
        scrollPane = new JScrollPane(historyArea);
        panel.add(scrollPane, BorderLayout.EAST);

        // Labels and input
        welcomeLabel.setText("Bem vindo " + player.getName() + ".");
        correctionLabel = new JLabel("Adivinha o número entre 1 e " + game.getRange() + ".");
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

        submitButton = new JButton("Enviar palpite.");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 18));
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        restartButton = new JButton("Reiniciar");
        restartButton.setFont(new Font("Arial", Font.PLAIN, 18));
        restartButton.addActionListener(e -> {
            panel.removeAll();
            initialize();
            panel.revalidate();
            panel.repaint();
        });

        // Low panel for buttons
        lowPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lowPanel.add(restartButton);
        panel.add(lowPanel, BorderLayout.SOUTH);

        // Game panel for main game elements
        gamePanel = new JPanel(new GridLayout(5, 1, 2, 2));
        gamePanel.add(welcomeLabel);
        gamePanel.add(correctionLabel);
        gamePanel.add(guessField);
        gamePanel.add(submitButton);
        gamePanel.add(triesLabel);
        panel.add(gamePanel);

        // Update the panel
        panel.revalidate();
        panel.repaint();
    }

    private void checkGuess() {
        try {
            guess = Integer.parseInt(guessField.getText());

            if (guess < 1 || guess > game.getRange()) {
                correctionLabel.setText("Por favor, insira um número entre 1 e " + game.getRange() + ".");
                guessField.setText("");
                correctionLabel.setForeground(Color.RED);
                return;
            }

            triesLabel.setText("Tentativas: " + game.getTries());
            historyArea.append("Tentativa " + game.getTries() + ": " + guess + "\n");

            if (game.checkGuess(guess)) {
                victorypanel();
            } else if (game.isHigher(guess)) {
                correctionLabel.setText("Errado! O número correto é maior que " + guess + ".");
                guessField.setText("");
                correctionLabel.setForeground(Color.GREEN);
            } else {
                correctionLabel.setText("Errado! O número correto é menor que " + guess + ".");
                guessField.setText("");
                correctionLabel.setForeground(Color.BLUE);
            }
        } catch (NumberFormatException e) {
            correctionLabel.setText("Por favor, insira um número válido.");
        }
    }

    private void victorypanel() {
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
        buttonPanel.setBackground(panelBackgroundColor);
        restartButton.addActionListener(e -> {
            panel.removeAll();
            initialize();
            panel.revalidate();
            panel.repaint();
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
