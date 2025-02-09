package src.ticTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame {
    private TicTacToeGame game;
    private JButton[][] buttons;
    private JLabel statusLabel;

    public TicTacToeGUI(TicTacToeGame game) {
        this.game = game;
        int boardSize = game.getBoard().getSize();
        buttons = new JButton[boardSize][boardSize];
        setUpGUI(boardSize);
    }

    private void setUpGUI(int boardSize) {
        setTitle("Tic Tac Toe");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(boardSize, boardSize));
        statusLabel = new JLabel("Player " + game.getCurrentPlayer().getName() + " (" + game.getCurrentPlayer().getSymbol() + ")'s turn", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 80));
                buttons[i][j].setFocusPainted(false);
                final int row = i;
                final int col = j;
                buttons[i][j].addActionListener(e -> handleButtonClick(row, col));
                boardPanel.add(buttons[i][j]);
            }
        }

        add(boardPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void handleButtonClick(int row, int col) {
        if (game.getBoard().makeMove(row, col, game.getCurrentPlayer().getSymbol())) {
            buttons[row][col].setText(String.valueOf(game.getCurrentPlayer().getSymbol()));
            buttons[row][col].setEnabled(false);

            if (game.checkWinner()) {
                JOptionPane.showMessageDialog(this, "Player " + game.getCurrentPlayer().getName() + " wins!");
                resetGame();
                return;
            }

            if (game.isBoardFull()) {
                JOptionPane.showMessageDialog(this, "It's a tie!");
                resetGame();
                return;
            }

            game.switchPlayer();
            updateStatus();
        }
    }

    private void updateStatus() {
        statusLabel.setText("Player " + game.getCurrentPlayer().getName() + " (" + game.getCurrentPlayer().getSymbol() + ")'s turn");
    }

    private void resetGame() {
        game.resetGame();
        int size = game.getBoard().getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
        updateStatus();
    }
}

