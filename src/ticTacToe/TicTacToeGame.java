package ticTacToe;

import common.Game;
import common.Player;

public class TicTacToeGame implements Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public TicTacToeGame(Player player1, Player player2) {
        this.board = new Board(3);
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        start();
    }
    @Override
    public void start(){
       new TicTacToeGUI(this);
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public boolean checkWinner() {
        return board.checkWinner(currentPlayer.getSymbol());
    }

    public boolean isBoardFull() {
        return board.isFull();
    }

    public void resetGame() {
        board.initializeBoard();
        currentPlayer = player1;
    }
}
