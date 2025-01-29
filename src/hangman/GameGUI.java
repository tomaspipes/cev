package hangman;

import javax.swing.*;

import common.Player;

import java.awt.*;

public class GameGUI {
    private static JFrame mainFrame;
    private static JPanel mainPanel;
    private static DifficultyWordChosing difficultyWordChosing;
    
         
    public GameGUI() {
        setupMainFrame();
        this.difficultyWordChosing = new DifficultyWordChosing();
    }

    static void setupMainFrame() {
        mainFrame = new JFrame("Hangman Game");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(900, 450);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        
        setupMainPanel();
    }
   
    static void setupMainPanel() {
        mainPanel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Bem-Vindo ao Hangman!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        welcomeLabel.setBounds(200, 200, 600, 50); // Specify exact location and size
        mainPanel.add(welcomeLabel, BorderLayout.CENTER);
        
        
        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.PLAIN, 18));
        
        //Se tiver que adicionar menu para escolha do jogador, nesta linha a seguir:
        //Em vez de chamar showDifficultySelection -> chama choosePlayerName()
        startButton.addActionListener(e -> showDifficultySelection(startButton));
        startButton.setBounds(450, 600, 100, 50);
        mainFrame.add(startButton, BorderLayout.SOUTH );
    
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }

    private static void showDifficultySelection(JButton startButton) {
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
       
        
        

    private static void startGame(String difficulty) {
        difficultyWordChosing.getRandomWord(difficulty);
        
        String word = difficultyWordChosing.getChosenWord();
        
        Player player = new Player("Jogador");
        new Game(mainPanel, player, word);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameGUI::new);
    }
}