package src.guess;

import src.common.Player;

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
				startGame();
				
			}
         });
         
         //add everything to panel
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
        	            default -> range = 100;}

        	        game = new GuessGame(range);
                            
                    setupGamePanel();         
         }
         
         private void setupGamePanel() {
        	 
        	 //clean panel
        	 panel.removeAll();
        	 //borderlayout to add restart at south and game in center
        	 panel.setLayout(new BorderLayout(2,2));
        	 
        	 //history
        	 historyArea = new JTextArea(5, 20);
        	 historyArea.setEditable(false);
        	 historyArea.setFont(new Font("Arial", Font.PLAIN, 22));
        	 scrollPane = new JScrollPane(historyArea);
        	 panel.add(scrollPane, BorderLayout.EAST);
        	 
        	 //second welcome
        	 welcomeLabel.setText("Bem vindo " + player.getName() + ".");
        	 
        	 //correctionlabel
        	 correctionLabel = new JLabel("Adivinha o número entre 1 e " + game.getRange() + ".");
        	 correctionLabel.setFont(new Font("Arial", Font.PLAIN, 26));
        	 
        	 //trieslabel
        	 triesLabel = new JLabel("Tentativas: " + game.getTries());
        	 triesLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        	 
        	 //guessfield
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
   
        	 //button
        	 submitButton = new JButton("Enviar palpite.");
        	 submitButton.setFont(new Font("Arial", Font.PLAIN, 18));
        	 submitButton.setSize(new Dimension(150, 40));
        	 submitButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					checkGuess();
					
				}
        		 
        	 });
        	 
        	 //button
        	 restartButton = new JButton("Reiniciar");
        	 restartButton.setFont(new Font("Arial", Font.PLAIN, 18));
        	 restartButton.setSize(new Dimension(150, 40));
        	 restartButton.addActionListener(new ActionListener() {
        		    @Override
        		    public void actionPerformed(ActionEvent e) {
        		        // Limpa o painel atual
        		        panel.removeAll();

        		        // Redefine o layout e os componentes iniciais
        		        initialize(); 

        		        // Atualiza o painel para refletir as mudanças
        		        panel.revalidate();
        		        panel.repaint();
        		    }
        		});

        	 //LowPanel for restart
        	 lowPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        	 lowPanel.add(restartButton);
        	 panel.add(lowPanel, BorderLayout.SOUTH);
        	 
        	 //gamepanel for game
        	 gamePanel = new JPanel(new GridLayout(5,1,2,2));
        	 panel.add(gamePanel);
        	 
        	 //add everything on gamepanel
        	 gamePanel.add(welcomeLabel);
        	 gamePanel.add(correctionLabel);
        	 gamePanel.add(guessField);
        	 gamePanel.add(submitButton);
        	 gamePanel.add(triesLabel);
        	 
        	 //recalcular e redesenhar o layout
        	 panel.revalidate();
        	 panel.repaint();
	 
         }
         private void checkGuess() {
		
        	 try {
        		 guess = Integer.parseInt(guessField.getText());
        		 
        		 //verifica se guess esta dentro da range
        		 if (guess < 1 || guess > game.getRange()) {
        	            correctionLabel.setText("Por favor, insira um número entre 1 e " + game.getRange() + ".");
        	            guessField.setText("");
        	            correctionLabel.setForeground(Color.RED);
        	            return;}
                 
                 //Update a tentativas no ecrã
                 triesLabel.setText("Tentativas: " + game.getTries());
                 
                 //Update a histórico
                 historyArea.append("Tentativa " + game.getTries() + ": " + guess + "\n");
                 
                 //lógica
                 if (game.checkGuess(guess)) {
                	 //vitória
                     victoryPanel();
                 } else if (game.isHigher(guess)) {                	 
                	 //correto é maior que guess
                     correctionLabel.setText("Errado! O número correto é maior que " + guess + ".");
                     guessField.setText("");//da clear a guessfield
                     correctionLabel.setForeground(Color.ORANGE);//cor para laranja
                 } else if (game.isLower(guess)){                	 
                	 //correto é menor que guess
                     correctionLabel.setText("Errado! O número correto é menor que " + guess + ".");
                     guessField.setText("");//da clear a guessfield
                     correctionLabel.setForeground(Color.ORANGE);}//cor para laranja
                 // se o número for inválido
             } catch (NumberFormatException e) {
                 correctionLabel.setText("Por favor, insira um número válido.");}
        	 				
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

             // Botões de reiniciar e sair
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

             // Adiciona os componentes ao painel
             panel.add(winMessage);
             panel.add(numberMessage);
             panel.add(buttonPanel);

             // Atualiza o painel
             panel.revalidate();
             panel.repaint();
         }
         
         
         }
