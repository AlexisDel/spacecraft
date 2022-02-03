package Model.Squares.NotContainers;

import Model.GameSquare;
import Model.Squares.NotContainer;

import java.awt.*;

import static View.BoardPanel.scaleSquare;
import static View.BoardPanel.tileSize;

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
        g.fillRect(x, y, tileSize*scaleSquare, tileSize*scaleSquare);
    }
}