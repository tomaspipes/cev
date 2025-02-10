package main;
import java.awt.*;

import java.util.Scanner;
import src.common.Player;
import src.guess.GuessGUI;
import src.ticTacToe.TicTacToeGame;
import src.hangman.HangmanGUI;

import javax.swing.*;

public class GameHub {
    private JFrame gameWindow;
    private JPanel gamePanel, onePlayerMenuPanel, twoPlayersMenuPanel;
    private JTextField nameField;
    private JButton startButton, guessTheNumberButton, hangmanButton, ticTacToeButton;
    public Player player1, player2;
    public boolean twoPlayerGame = false;
    private final Color panelBackgroundColor = new Color(224, 224, 224);

    public GameHub() {
        displayGamesMenu();
        CommandLogger.log("GameHub inicializado");
        startConsoleListener();
    }

    public void startConsoleListener() {
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String input = scanner.nextLine();
                if ("Hub".equalsIgnoreCase(input)) {
                    gameWindow.repaint();
                    gameWindow.dispose();
                    displayGamesMenu();
                }
            }
        }).start();
    }

    public void displayGamesMenu() {
        gameWindow = new JFrame("Escolha o Jogo");
        gameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gameWindow.setSize(800, 600);
        gameWindow.setLocationRelativeTo(null);
        
        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(4, 1, 10, 10));
        gamePanel.setBackground(panelBackgroundColor);

        JLabel gameChoiceLabel = new JLabel("Que jogo quer jogar?", SwingConstants.CENTER);
        gameChoiceLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        gamePanel.add(gameChoiceLabel);

        displayGuessTheNumberButton();
        displayHangmanButton();
        displayTicTacToeButton();

        gameWindow.add(gamePanel);
        gameWindow.setVisible(true);
    }

    public void displayGuessTheNumberButton() {
        guessTheNumberButton = new JButton("Guess the Number");
        guessTheNumberButton.setFont(new Font("Arial", Font.BOLD, 24));
        guessTheNumberButton.addActionListener(e -> displayOnePlayerMenu("GuessTheNumber"));
        gamePanel.add(guessTheNumberButton);
    }

    public void displayHangmanButton() {
        hangmanButton = new JButton("Hangman");
        hangmanButton.setFont(new Font("Arial", Font.BOLD, 24));
        hangmanButton.addActionListener(e -> displayOnePlayerMenu("Hangman"));
        gamePanel.add(hangmanButton);
    }

    public void displayTicTacToeButton() {
        ticTacToeButton = new JButton("Tic Tac Toe");
        ticTacToeButton.setFont(new Font("Arial", Font.BOLD, 24));
        ticTacToeButton.addActionListener(e -> displayTwoPlayerMenu());
        gamePanel.add(ticTacToeButton);
    }

    public void displayOnePlayerMenu(String gameName) {
        gameWindow.getContentPane().removeAll();
        
        onePlayerMenuPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        onePlayerMenuPanel.setBackground(panelBackgroundColor);
        
        JLabel nameLabel = new JLabel("Qual é o nome do jogador?", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        onePlayerMenuPanel.add(nameLabel);
        
        nameField = new JTextField();
        onePlayerMenuPanel.add(nameField);
        
        startButton = new JButton("Iniciar Jogo");
        startButton.addActionListener(e -> {
        player1 = new Player(nameField.getText());

        if ("GuessTheNumber".equals(gameName)) {
            new GuessGUI(player1);
            CommandLogger.log("Guess the Number game started by " + player1.getName());
        } else if ("Hangman".equals(gameName)) {
            SwingUtilities.invokeLater(() -> new HangmanGUI());
            CommandLogger.log("Hangman game started by " + player1.getName());
        }
            gameWindow.dispose();
        });

        onePlayerMenuPanel.add(startButton);

        gameWindow.add(onePlayerMenuPanel);
        gameWindow.revalidate();
        gameWindow.repaint();
    }

    public void displayTwoPlayerMenu() {
        gameWindow.getContentPane().removeAll();
        
        twoPlayersMenuPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        twoPlayersMenuPanel.setBackground(panelBackgroundColor);
        
        JLabel nameLabel = new JLabel("Quais são os nomes dos jogadores?", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        twoPlayersMenuPanel.add(nameLabel);
        
        JTextField player1Field = new JTextField();
        JTextField player2Field = new JTextField();
        twoPlayersMenuPanel.add(player1Field);
        twoPlayersMenuPanel.add(player2Field);
        
        startButton = new JButton("Iniciar Jogo");
        startButton.addActionListener(e -> {
            player1 = new Player(player1Field.getText());
            player2 = new Player(player2Field.getText());
            player1.setSymbol('X');
            player2.setSymbol('O');
            new TicTacToeGame(player1, player2);
            CommandLogger.log("Tic tac toe game started by " + player1.getName() + " and " + player2.getName());
            gameWindow.dispose();
        });
        twoPlayersMenuPanel.add(startButton);
        
        gameWindow.add(twoPlayersMenuPanel);
        gameWindow.revalidate();
        gameWindow.repaint();
    }
}
