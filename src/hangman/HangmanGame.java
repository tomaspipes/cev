package src.hangman;

import javax.swing.*;

import src.common.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HangmanGame {
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
    
    //contrutor que inicializa o jogo do hangMan
    public HangmanGame(JPanel parentPanel, Player player, String chosenWord) {
        this.mainPanel = parentPanel;
        this.player = player;
        this.selectedWord = chosenWord.toUpperCase();
        this.currentGuess = new char[chosenWord.length()];
        this.wrongGuesses = new ArrayList<>();

        //realiza um loop por cada letra da palavra aleatória
        for (int i = 0; i < currentGuess.length; i++) {
            currentGuess[i] = chosenWord.charAt(i) == ' ' ? ' ' : '_'; //se o caracter for um espaço mantem-se, se não é colocado um _ em vez da letra
        }

        setupGameGUI(); //chama o método para configurar a interface do jogo
    }

    //método para configurar a interface do jogo
    private void setupGameGUI() {
        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());

        //cria um novo painel com o nome de hangmanPanel
        hangmanPanel = new HangmanPanel();
        hangmanPanel.setPreferredSize(new Dimension(500, 500));

        //cria a label para apresentar ao jogador quantas tentativas restam
        triesLabel = new JLabel("Tentativas restantes: " + triesLeft);
        triesLabel.setFont(new Font("Arial", Font.BOLD, 16));

        //cria um label para apresentar ao jogador quais a letras colocadas estão erradas
        wrongLettersLabel = new JLabel("Letras erradas: ");
        wrongLettersLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        //cria um painel para adicionar as letras erradas e as tentativas restantes
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.add(triesLabel, BorderLayout.NORTH);
        infoPanel.add(wrongLettersLabel, BorderLayout.SOUTH);

        //cria um painel para adicionar o hangman e o infoPanel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(hangmanPanel, BorderLayout.EAST);
        topPanel.add(infoPanel, BorderLayout.WEST);

        //cria uma "label" que contêm a palavra a ser adivinhada (aprensentada como _ _ _ _) onde cada vez que a letra introduzida estiver correta troca o _ pela letra
        wordLabel = new JLabel(formatCurrentGuess());
        wordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        wordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        

        //cria um campo onde o utilizador pode colocar a letra que pretende para adivinhar a palavra
        guessField = new JTextField();
        JButton guessButton = new JButton("Adivinhar"); //cria um botão para o player confirmar o seu input
        guessButton.addActionListener(e -> handleGuess(guessField.getText().toUpperCase().trim())); //permite que ao clicar no botão o user confirme a sua jogada
        guessField.addActionListener(e -> handleGuess(guessField.getText().toUpperCase().trim())); //permite que o player clique enter para confirmar o seu input

        //cria um painel apra o input do utilizador
        JPanel guessPanel = new JPanel(new BorderLayout());
        guessPanel.add(guessField, BorderLayout.CENTER); //adiciona o guessField
        guessPanel.add(guessButton, BorderLayout.EAST); //adiciona o guessButton

        //cria um painel para adicionar o campo de resposta e o botão
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(wordLabel, BorderLayout.NORTH);
        bottomPanel.add(guessPanel, BorderLayout.SOUTH);

        //adiciona os dois Panels criados ao mainPanel
        mainPanel.add(topPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        
        mainPanel.revalidate(); //atualiza o mainPainel
        mainPanel.repaint(); //dá re-render ao mainPanel

        //método para validar o input do player
    private void handleGuess(String input) {
        if (input.length() != 1 || !Character.isLetter(input.charAt(0))) { //garante que apenas pode ser colocada uma letra
            JOptionPane.showMessageDialog(mainPanel, "Apenas uma letra válida é permitida!", "Erro", JOptionPane.ERROR_MESSAGE); //mensagem de erro caso não seja apenas uma letra colocada
            guessField.setText(""); //limpa o campo de input do utilizador
            return;
        }

        char guessedLetter = input.charAt(0); //guarda a letra introduzida pelo player

      //confirma se a letra introduzida está na palavra
        if (selectedWord.contains(String.valueOf(guessedLetter))) { 
            for (int i = 0; i < selectedWord.length(); i++) {
                if (selectedWord.charAt(i) == guessedLetter) {
                    currentGuess[i] = guessedLetter;
                }
            }
            
          //se a letra introduzida estiver errada e não foi anteriormente escolhida
        } else if (!wrongGuesses.contains(guessedLetter)) {
            wrongGuesses.add(guessedLetter); //adicionada á label das letras erradas
            triesLeft--; //retira uma tentativa do jogador
        }
        
        guessField.setText(""); //limpa o campo de input

        updateGameStatus(); //chama o método para atualizar o estado atual do jogo
    }

    //atualiza o GUI consoante o estado do jogo
    private void updateGameStatus() {
        wordLabel.setText(formatCurrentGuess());
        wrongLettersLabel.setText("Letras erradas: " + wrongGuesses);
        triesLabel.setText("Tentativas restantes: " + triesLeft);
        hangmanPanel.setTriesLeft(triesLeft);

        //verifica se a palavra a ser adivinhada é igual á palavra escolhida aleatóriamente
        if (String.valueOf(currentGuess).equals(selectedWord)) {
            JOptionPane.showMessageDialog(mainPanel, "Parabéns, você venceu!", "Vitória", JOptionPane.INFORMATION_MESSAGE); //se forem iguais aparece uma janela a parabenizar o jogador
            returnToMenu(); //chama o método para voltar ao Menu
        } else if (triesLeft == 0) { //verifica se a palavra é diferente da escolhida e o número de tentativas for igual a zero
            JOptionPane.showMessageDialog(mainPanel, "Game Over! A palavra era: " + selectedWord, "Derrota", JOptionPane.ERROR_MESSAGE); //se as tentativas se esgotarem o jogador terá a janela de derrota
            returnToMenu(); //chama o método para voltar ao Menu
        }
    }

    //Formata o input do jogador para uma string onde é possivel visualizar a letra introduzida
    private String formatCurrentGuess() {
        StringBuilder formatted = new StringBuilder();
        for (char c : currentGuess) {
            formatted.append(c).append(" ");
        }
        return formatted.toString().trim();
    }

    //método para reiniciar o jogo e voltar para o menu inicial
    private void returnToMenu() {
    	 mainPanel.removeAll();
    	 mainPanel.revalidate();
    	 mainPanel.repaint();
    	 
    	 //recupera o JFrame que contem o mainPanel (utilizado para encontrar as janelas)
    	 JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
    	    
    	    if (topFrame != null) {
    	        topFrame.dispose(); //Fecha e remove a janela atual
    	    }
        
        
        HangmanGUI.setupMainFrame(); // Abre um novo mainFrame
    }
    
}
