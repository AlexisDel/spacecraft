package Model;

/**
 * Classe du moteur du jeu
 */
public class GameEngine {

    // Le plateau du jeu
    private GameBoard gameBoard;
    private boolean isGameRunning;

    public GameEngine() {
        gameBoard = new GameBoard();
        isGameRunning = true;

    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public boolean isGameRunning() {
        return isGameRunning;
    }
}
