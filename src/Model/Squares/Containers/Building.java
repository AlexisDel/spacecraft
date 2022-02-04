package Model.Squares.Containers;

import Model.GameSquare;
import Model.Squares.Container;

import java.awt.*;

/**
 * Case représentant un bâtiment
 */
public class Building extends Container implements GameSquare {

    public Building() {
        super();
    }

    @Override
    public void draw(Graphics2D g, int x, int y, int boardTileSize, int tileSize) {
        g.setColor(Color.RED);
        g.fillRect(x, y, boardTileSize, boardTileSize);
    }

    @Override
    public void clicked(int localX, int localY) {
        System.out.println("Building !");
    }
}
