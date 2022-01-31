package View;

import Model.GameEngine;

public class View {

    private GUI gui;
    private BoardView boardView;

    public View(GameEngine gameEngine) {
        gui = new GUI("SpaceCraft");
        boardView = new BoardView(gameEngine.getBoard());

        gui.add(boardView);
    }
}
