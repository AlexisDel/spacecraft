package Model.Squares.NotContainers;

import Model.GameSquare;
import Model.Squares.NotContainer;

import java.awt.*;

import static View.BoardPanel.boardTileSize;

/**
 * Case représentant une montagne
 */
public class Mountain extends NotContainer implements GameSquare {

    public Mountain() {
        super();
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        g.setColor(Color.GRAY);
        g.fillRect(x, y, boardTileSize, boardTileSize);
    }

    @Override
    public void clicked(int localX, int localY) {

    }
}