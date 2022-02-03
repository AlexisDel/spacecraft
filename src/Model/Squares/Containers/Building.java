package Model.Squares.Containers;

import Model.GameSquare;
import Model.Squares.Container;

import java.awt.*;

import static View.BoardPanel.scaleSquare;
import static View.BoardPanel.tileSize;

/**
 * Case représentant un bâtiment
 */
public class Building extends Container implements GameSquare {

    public Building() {
        super();
    }

    @Override
    public void draw(Graphics2D g, int i, int j) {
        g.setColor(Color.RED);
        g.fillRect(i*tileSize*scaleSquare, j*tileSize*scaleSquare, tileSize*scaleSquare, tileSize*scaleSquare);
    }
}
