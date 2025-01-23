package main;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import src.Player;
import src.guess.GuessGUI;
import src.ticTacToe.TicTacToeGame;

import javax.swing.*;

	public class GameHub {
	    private JFrame window;
	    private JPanel panel;
	    private JTextField nameField;
	    private JButton startButton, guessTheNumberButton, hangmanButton, ticTacToeButton;
	    public Player player; // Classe Player que guarda o nome do jogador
	    private final Color panelBackgroundColor = new Color(224, 224, 224);
	    
	    public GameHub() {
	        // Primeira Janela - Solicitar o nome
	        window = new JFrame("Hub de Jogos do IADE");
	        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        window.setSize(800, 600);
	        window.setLocationRelativeTo(null);

	        // Painel para a janela inicial
	        panel = new JPanel();
	        panel.setLayout(new GridLayout(4, 1, 10, 10));
	        panel.setBackground(panelBackgroundColor);

	        // Título do Hub
	        JLabel welcomeLabel = new JLabel("Bem-vindo ao Hub de Jogos do IADE", SwingConstants.CENTER);
	        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 36));
	        panel.add(welcomeLabel);

	        JLabel nameLabel = new JLabel("Qual é o nome do jogador?", SwingConstants.CENTER);
	        nameLabel.setFont(new Font("Arial", Font.PLAIN, 24));
	        panel.add(nameLabel);

	        nameField = new JTextField();
	        nameField.setFont(new Font("Arial", Font.PLAIN, 24));
	        panel.add(nameField);

	        // Botão para começar
	        startButton = new JButton("Iniciar");
	        startButton.setFont(new Font("Arial", Font.BOLD, 24));
	        startButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String playerName = nameField.getText();
	                if (playerName.isEmpty()) {
	                    JOptionPane.showMessageDialog(window, "Por favor, insira um nome!", "Erro", JOptionPane.ERROR_MESSAGE);
	                } else {
	                    player = new Player(playerName); // Cria o jogador com o nome
	                    showGameSelectionWindow(); // Mostra a tela de seleção de jogo
	                    window.dispose(); // Fecha a janela atual
	                }
	            }
	        });
	        nameField.addKeyListener(new KeyAdapter() {
	            @Override
	            public void keyPressed(KeyEvent e) {
	                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	                    // Este código será executado quando a tecla "Enter" for pressionada
	                    String playerName = nameField.getText();
	                    if (playerName.isEmpty()) {
	                        JOptionPane.showMessageDialog(window, "Por favor, insira um nome!", "Erro", JOptionPane.ERROR_MESSAGE);
	                    } else {
	                        player = new Player(playerName); // Cria o jogador com o nome
	                        showGameSelectionWindow(); // Mostra a tela de seleção de jogo
	                        window.dispose(); // Fecha a janela atual
	                    }
	                }
	            }
	        });
	        
	        panel.add(startButton);

	        // Adiciona o painel à janela e torna visível
	        window.add(panel);
	        window.setVisible(true);
	    }

	    private void showGameSelectionWindow() {
	        // Nova Janela - Seleção de Jogo
	        JFrame gameWindow = new JFrame("Escolha seu Jogo");
	        gameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        gameWindow.setSize(800, 600);
	        gameWindow.setLocationRelativeTo(null);

	        JPanel gamePanel = new JPanel();
	        gamePanel.setLayout(new GridLayout(5, 1, 10, 10));
	        gamePanel.setBackground(panelBackgroundColor);

	        // Boas-vindas ao jogador
	        JLabel welcomeMessage = new JLabel("Bem-vindo " + player.getName(), SwingConstants.CENTER);
	        welcomeMessage.setFont(new Font("Arial", Font.BOLD, 36));
	        gamePanel.add(welcomeMessage);

	        JLabel gameChoiceLabel = new JLabel("Que jogo quer jogar?", SwingConstants.CENTER);
	        gameChoiceLabel.setFont(new Font("Arial", Font.PLAIN, 24));
	        gamePanel.add(gameChoiceLabel);

	        // Botão para "Guess the Number"
	        guessTheNumberButton = new JButton("Guess the Number");
	        guessTheNumberButton.setFont(new Font("Arial", Font.BOLD, 24));
	        guessTheNumberButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                new GuessGUI(player); // Inicia o jogo "Guess the Number"
	                gameWindow.dispose(); // Fecha a janela de seleção de jogo
	            }
	        });
	        gamePanel.add(guessTheNumberButton);

	        // Botão para "Hangman" (a ser implementado)
	        hangmanButton = new JButton("Hangman");
	        hangmanButton.setFont(new Font("Arial", Font.BOLD, 24));
	        hangmanButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                JOptionPane.showMessageDialog(gameWindow, "Hangman ainda não está implementado!");
	            }
	        });
	        gamePanel.add(hangmanButton);

	        // Botão para "Tic Tac Toe" (a ser implementado)
	        ticTacToeButton = new JButton("Tic Tac Toe");
	        ticTacToeButton.setFont(new Font("Arial", Font.BOLD, 24));
	        ticTacToeButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
                    Player player1 = new Player("Tomás");
                    Player player2 = new Player("Silvia");
                    player1.setSymbol('X');
                    player2.setSymbol('O');
	                new TicTacToeGame(player1, player2); // Inicia o jogo "Tic Tac Toe"
                    gameWindow.dispose(); // Fecha a janela de seleção de jogo
                }
	        });
	        gamePanel.add(ticTacToeButton);

	        // Adiciona o painel à janela e torna visível
	        gameWindow.add(gamePanel);
	        gameWindow.setVisible(true);
	    }

	    public static void main(String[] args) {
	        new GameHub(); // Inicia o Hub de Jogos
	    }
	}
