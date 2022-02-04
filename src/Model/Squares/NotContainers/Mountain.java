package Model.Squares.NotContainers;

import Model.GameSquare;
import Model.Squares.NotContainer;

import java.awt.*;

/**
 * Case représentant une montagne
 */
public class Mountain extends NotContainer implements GameSquare {

    public Mountain() {
        super();
    }

    @Override
    public void draw(Graphics2D g, int x, int y, int boardTileSize, int tileSize) {
        g.setColor(Color.GRAY);
        g.fillRect(x, y, boardTileSize, boardTileSize);
    }

    @Override
    public void clicked(int localX, int localY) {

    }
}