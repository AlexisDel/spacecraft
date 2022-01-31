import Model.GameEngine;
import View.View;

public class Main {

    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        View view = new View(gameEngine);
    }
}
