package src.hangman;

import javax.swing.*; //importa a biblioteca Swing para os componentes GUI
import java.awt.*; //importa a biblioteca AWT para renderização do gráficos

public class HangmanPanel extends JPanel { //define a class que extende a class JPanel
    private int triesLeft; //instancia a variável para seguir as tentativas restantes do player

    public HangmanPanel() {
    	super(); //"chama" o construtor da superclass JPanel
        this.triesLeft = 10; // Número de tentativas iniciais (totais)
    }

    public void setTriesLeft(int triesLeft) {
        this.triesLeft = triesLeft; //atualiza o número de tentativas restantes
        repaint();  // atualiza o painel quando o número de tentativas diminui
    }

    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //Elimina os componentes do painel para desenhar o novo estado da página
        drawHangman(g, triesLeft); //"chama" o método para desenhar o "hangman" com base nas tentativas restantes
    }

    private void drawHangman(Graphics g, int triesLeft) {
        g.setColor(Color.BLACK);

        // Desenha o poste
        if (triesLeft < 10) g.drawLine(-125, 200, 115, 200);
        if (triesLeft < 9) g.drawLine(50, 200, 50, 50);
        if (triesLeft < 8) g.drawLine(50, 50, 150, 50);
        if (triesLeft < 7) g.drawLine(150, 50, 150, 70);

        // Desenha o corpo do hangman
        if (triesLeft < 6) g.drawOval(140, 70, 20, 20); // Cabeça
        if (triesLeft < 5) g.drawLine(150, 90, 150, 150); // Corpo
        if (triesLeft < 4) g.drawLine(150, 110, 120, 130); // Braço esquerdo
        if (triesLeft < 3) g.drawLine(150, 110, 180, 130); // Braço direito
        if (triesLeft < 2) g.drawLine(150, 150, 120, 170); // Perna esquerda
        if (triesLeft < 1) g.drawLine(150, 150, 180, 170); // Perna direita
    }
}
