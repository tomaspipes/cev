package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import src.Player;
import src.guess.GuessGUI;
import src.ticTacToe.TicTacToeGame;
//import src.hangman.HangmanGame;
//import src.hangman.HangmanPanel;

public class GameHub {

    private JFrame window;
    private JPanel panel;
    private JButton guessTheNumberButton, hangmanButton, ticTacToeButton;
    private Player player1, player2;
    private final Color panelBackgroundColor = new Color(224, 224, 224);

    public GameHub() {
        // Primeira Janela - Seleção de Jogo
        window = new JFrame("Hub de Jogos do IADE");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);
        window.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBackground(panelBackgroundColor);

        JLabel welcomeLabel = new JLabel("Welcome to IADE Games Hub", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Calibri", Font.BOLD, 36));
        panel.add(welcomeLabel);

        JLabel gameChoiceLabel = new JLabel("Que jogo quer jogar?", SwingConstants.CENTER);
        gameChoiceLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(gameChoiceLabel);

        // Botão para "Guess the Number"
        guessTheNumberButton = new JButton("Guess the Number");
        guessTheNumberButton.setFont(new Font("Arial", Font.BOLD, 24));
        guessTheNumberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requestPlayerName(1, "Guess the Number");
            }
        });
        panel.add(guessTheNumberButton);

        // Botão para "Hangman"
        hangmanButton = new JButton("Hangman");
        hangmanButton.setFont(new Font("Arial", Font.BOLD, 24));
        hangmanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requestPlayerName(1, "Hangman");
            }
        });
        panel.add(hangmanButton);

        // Botão para "Tic Tac Toe"
        ticTacToeButton = new JButton("Tic Tac Toe");
        ticTacToeButton.setFont(new Font("Arial", Font.BOLD, 24));
        ticTacToeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requestPlayerName(2, "Tic Tac Toe");
            }
        });
        panel.add(ticTacToeButton);

        window.add(panel);
        window.setVisible(true);
    }

    private void requestPlayerName(int playerCount, String game) {
        JFrame nameWindow = new JFrame("Jogador(es)");
        nameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        nameWindow.setSize(400, 300);
        nameWindow.setLocationRelativeTo(null);

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new GridLayout(playerCount + 2, 1, 10, 10));
        namePanel.setBackground(panelBackgroundColor);

        JLabel instructionLabel = new JLabel("Insira o nome do(s) jogador(es):", SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        namePanel.add(instructionLabel);

        JTextField player1Field = new JTextField();
        player1Field.setFont(new Font("Arial", Font.PLAIN, 18));
        namePanel.add(player1Field);

        JTextField player2Field = null;
        if (playerCount == 2) {
            player2Field = new JTextField();
            player2Field.setFont(new Font("Arial", Font.PLAIN, 18));
            namePanel.add(player2Field);
        }

        JButton startGameButton = new JButton("Iniciar Jogo");
        startGameButton.setFont(new Font("Arial", Font.BOLD, 18));
        JTextField finalPlayer2Field = player2Field;
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name1 = player1Field.getText();
                if (name1.isEmpty() || (playerCount == 2 && finalPlayer2Field.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(nameWindow, "Por favor, insira o(s) nome(s)!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                player1 = new Player(name1);
                if (playerCount == 2) {
                    player2 = new Player(finalPlayer2Field.getText());
                    player1.setSymbol('X');
                    player2.setSymbol('O');
                }

                nameWindow.dispose();
                startGame(game);
            }
        });

        namePanel.add(startGameButton);
        nameWindow.add(namePanel);
        nameWindow.setVisible(true);
    }

    private void startGame(String game) {
        switch (game) {
            case "Guess the Number":
                new GuessGUI(player1);
                break;
            case "Hangman":
                //JOptionPanel.showMessageDialog(gameWindow, "Hangman ainda não está implementado!");
                break;
            case "Tic Tac Toe":
                new TicTacToeGame(player1, player2);
                break;
        }
    }
}
