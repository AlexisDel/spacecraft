package Model.Entities.Character;

import Model.Entities.Entity;

import java.awt.*;

import static View.BoardPanel.tileSize;

public class Alien implements Entity {

    @Override
    public void draw(Graphics2D g, int x, int y) {
            g.setColor(Color.GREEN);
            g.fillRect(x, y, tileSize, tileSize);
    }
}
