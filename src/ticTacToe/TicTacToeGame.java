package src.ticTacToe;

import src.Game;

public class TicTacToeGame implements Game {
    private Board board;
    private PlayerTicTacToe player1;
    private PlayerTicTacToe player2;
    private PlayerTicTacToe currentPlayer;

    public TicTacToeGame(PlayerTicTacToe player1, PlayerTicTacToe player2, int boardSize) {
        this.board = new Board(boardSize);
        this.player1 = new PlayerTicTacToe(player1.getName(), 'X');
        this.player2 = new PlayerTicTacToe(player2.getName(), 'O');
        this.currentPlayer = player1;
        start();
    }
    @Override
    public void start(){
       GUI gui = new GUI(this);
    }

    public Board getBoard() {
        return board;
    }

    public PlayerTicTacToe getCurrentPlayer() {
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
