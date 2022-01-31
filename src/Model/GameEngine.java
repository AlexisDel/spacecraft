package Model;
/**modèle principal du jeu*/

public class GameEngine {

    private Board board;

    public GameEngine() {
        this.board = new Board();
    }

    public Board getBoard() {
        return this.board;
    }
}
