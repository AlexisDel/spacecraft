import Model.GameEngine;
import View.View;
import Model.Board;
public class Main {

    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        View view = new View(gameEngine);
    }
}
