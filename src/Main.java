import Controller.GameController;
import Model.GameEngine;
import View.GameView;

public class Main {

    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        GameView gameView = new GameView(gameEngine);
        GameController gameController = new GameController(gameView);

        gameView.setController(gameController);
    }
}
