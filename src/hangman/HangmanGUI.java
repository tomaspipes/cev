package src.hangman;

import javax.swing.*;
import java.awt.*;

public class HangmanGameGUI {
    private JFrame mainFrame;
    private JPanel mainPanel;
    
         
    public HangmanGameGUI() {
        setupMainFrame();
    }

    void setupMainFrame() {
        mainFrame = new JFrame("Hangman Game");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1000, 1000);
        mainFrame.setLocationRelativeTo(null);
        
        mainPanel = new JPanel(null);  // Use null layout manager for precise control over component positioning

        mainPanel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Bem-Vindo ao Hangman!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        welcomeLabel.setBounds(200, 200, 600, 50); // Specify exact location and size
        mainPanel.add(welcomeLabel, BorderLayout.CENTER);


        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.PLAIN, 18));
        startButton.addActionListener(e -> showDifficultySelection(startButton));
        startButton.setBounds(450, 600, 100, 50);
        mainFrame.add(startButton);

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }

    private void showDifficultySelection(JButton startButton) {
    	mainPanel.removeAll();

    	startButton.setVisible(false); // Remove the start button from the panel
       
        mainPanel.revalidate();  // Ensure the panel is updated
        mainPanel.repaint();     // Redraw the components
        
        JLabel difficultyLabel = new JLabel("Escolha uma Dificuldade:");
               
        difficultyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        difficultyLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        String[] difficulties = {"Fácil", "Médio", "Difícil", "Impossivel"};
        for (String difficulty : difficulties) {
            JButton button = new JButton(difficulty);
            button.addActionListener(e -> startGame(difficulty));
            buttonPanel.add(button);
        }

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> setupMainFrame());

        mainPanel.setLayout(new BorderLayout());
        
        mainPanel.add(difficultyLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(backButton, BorderLayout.SOUTH);
        mainPanel.revalidate();
        mainPanel.repaint();

        }

    private void startGame(String difficulty) {
        String word = "JAVA"; // Você pode ajustar com base na dificuldade escolhida
        Player player = new Player("Jogador");
        new HangmanGame(mainPanel, player, word);
    }
}
