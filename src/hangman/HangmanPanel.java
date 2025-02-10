package hangman;

import javax.swing.*;
import java.awt.*;

public class HangmanPanel extends JPanel {
    private int triesLeft;

    public HangmanPanel() {
    	super();
        this.triesLeft = 10; // Número de tentativas iniciais
    }

    public void setTriesLeft(int triesLeft) {
        this.triesLeft = triesLeft;
        repaint();  // Repaint the panel when tries change
    }

    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawHangman(g, triesLeft);
    }

    private void drawHangman(Graphics g, int triesLeft) {
        g.setColor(Color.BLACK);

        // Desenha o poste
        if (triesLeft < 10) g.drawLine(-125, 200, 115, 200);
        if (triesLeft < 9) g.drawLine(50, 200, 50, 50);
        if (triesLeft < 8) g.drawLine(50, 50, 150, 50);
        if (triesLeft < 7) g.drawLine(150, 50, 150, 70);

        // Desenha o corpo do enforcado
        if (triesLeft < 6) g.drawOval(140, 70, 20, 20); // Cabeça
        if (triesLeft < 5) g.drawLine(150, 90, 150, 150); // Corpo
        if (triesLeft < 4) g.drawLine(150, 110, 120, 130); // Braço esquerdo
        if (triesLeft < 3) g.drawLine(150, 110, 180, 130); // Braço direito
        if (triesLeft < 2) g.drawLine(150, 150, 120, 170); // Perna esquerda
        if (triesLeft < 1) g.drawLine(150, 150, 180, 170); // Perna direita
    }
}
