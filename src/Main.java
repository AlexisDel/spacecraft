import Controller.BoardController;
import Model.GameEngine;
import View.GameView;

public class Main {

    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        GameView gameView = new GameView(gameEngine);
        BoardController boardController = new BoardController(gameView);

        gameView.setBoardController(boardController);
    }
}
