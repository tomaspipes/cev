package src.hangman;

import javax.swing.*; // Importa a biblioteca Swing

import src.common.Player; // Importa a class Player do "common" package

import java.awt.*; // Importa a biblioteca AWT

public class HangmanGUI { //Main GUI class para o jogo HangMan
    private static JFrame mainFrame; // Nomeia a mainFrame do jogo
    private static JPanel mainPanel;  // Nomeia o mainPanel
    private static DifficultyWordChosing difficultyWordChosing;  // Declara uma instancia da class DifficultyWordChosing
    private static Player player;  // Declara o objeto Player para guardar o input do jogador

    
    public HangmanGUI(Player player) { //Construtor da class HangmanGUI que inicia o GUI
        setupMainFrame(); // "chama" o método para dar setup do mainFrame inicial
        this.player = player; //atribui o jogador passado como paramentro á variável interna da class
        this.difficultyWordChosing = new DifficultyWordChosing();  // Inicializa uma instancia de DifficultyWordChosing
    }

    static void setupMainFrame() { //método para configurar o mainFrame
        mainFrame = new JFrame("Hangman Game"); //cria um JFrame com o titulo do jogo
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //aplicação fecha quando a janela é fechada
        mainFrame.setSize(900, 450); //dimensão do Frame
        mainFrame.setResizable(false); //não pode ser alterado o tamanho da janela
        mainFrame.setLocationRelativeTo(null); //centra a janela no meio do ecrã do user
        
        setupMainPanel(); //"chama" o método para configurar o mainPanel
    }
   
    static void setupMainPanel() { //método para configurar o mainPanel
        mainPanel = new JPanel(new BorderLayout()); //cria um novo JPanel 
        JLabel welcomeLabel = new JLabel("Bem-Vindo ao Hangman " +player.getName()+ "!"); //cria uma "Label" a dar as boas vindas ao player
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER); //centra o texto
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30)); //torna a letra da "label" em arial, negrito com o tamanho 30
        welcomeLabel.setBounds(200, 200, 600, 50); // especifica a localização
        mainPanel.add(welcomeLabel, BorderLayout.CENTER); //adiciona o welcomeLabel no centro do mainPanel
        
        
        JButton startButton = new JButton("Start"); //cria um botão para começar
        startButton.setFont(new Font("Arial", Font.PLAIN, 18)); //especifica o estilo de letra e o tamanho da mesma
        
        startButton.addActionListener(e -> showDifficultySelection(startButton)); //adiciona um "event listner" que aciona quando o botão é clicado
        startButton.setBounds(450, 600, 100, 50); //especifica a localização do botão
        mainFrame.add(startButton, BorderLayout.SOUTH ); //adiciona o botão á mainFrame
    
        mainFrame.add(mainPanel); //adiciona o mainPanel á mainFrame
        mainFrame.setVisible(true); //torna os componentes do mainFrame e o próprio mainFrame visiveis
    }

    private static void showDifficultySelection(JButton startButton) { //método para aprensentar a escolha da dificuldade ao player
    	mainPanel.removeAll(); //remove todos os componentes do mainPanel
       
    	startButton.setVisible(false); // Esconde o botão start do mainPanel
       
        mainPanel.revalidate();  // atualiza o mainPanel
        mainPanel.repaint();     // re-render os componentes do mainPanel
        
        //cria uma label para o user
        JLabel difficultyLabel = new JLabel("Escolha uma Dificuldade:"); //
               
        difficultyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        difficultyLabel.setFont(new Font("Arial", Font.BOLD, 20));

        //cria um array (4 filas, 1 coluna) para a criar botões com as dificuldades
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        String[] difficulties = {"Fácil", "Médio", "Difícil", "Impossivel"};
        for (String difficulty : difficulties) {
            JButton button = new JButton(difficulty);
            button.addActionListener(e -> startGame(difficulty)); //cada botão é uma dificuldade diferente
            buttonPanel.add(button);
        }

        //cria um botão para voltar atrás
        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> setupMainFrame()); //volta para o mainFrame inicial

        mainPanel.setLayout(new BorderLayout());
        
        mainPanel.add(difficultyLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(backButton, BorderLayout.SOUTH);
        mainPanel.revalidate();
        mainPanel.repaint();
    
        }
       

    //Método para inicializar o jogo com a dificuldade escolhida
    private static void startGame(String difficulty) {
        difficultyWordChosing.getRandomWord(difficulty);
        
        String word = difficultyWordChosing.getChosenWord();
        
        Player player = new Player();
        new HangmanGame(mainPanel, player, word);
    }
}
