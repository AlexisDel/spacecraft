package View;
import Model.GameEngine;

public class View {

    private GUI gui;
    private BoardView boardView;

    public View(GameEngine gameEngine) {
        this.gui = new GUI("SpaceCraft");
        this.boardView = new BoardView(gameEngine.getBoard());
        this.gui.add(boardView);
        this.gui.setVisible(true);

    }
}
