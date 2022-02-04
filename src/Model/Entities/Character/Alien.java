package Model.Entities.Character;

import Model.Entities.Entity;

import java.awt.*;

public class Alien implements Entity {

    @Override
    public void draw(Graphics2D g, int x, int y, int tileSize) {
            g.setColor(Color.GREEN);
            g.fillRect(x, y, tileSize, tileSize);
    }
}
