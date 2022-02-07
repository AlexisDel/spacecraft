package View.Tiles;

import Model.Layer1.Entities.Alien;

import java.awt.*;

public class AlienView implements ObjectView{

    Alien alien;

    public AlienView(Alien alien) {
        this.alien = alien;
    }

    @Override
    public void draw(Graphics2D g, int tileSize, int displayX, int displayY) {
        g.setColor(Color.GREEN);
        g.fillRect((alien.getCoordinate().x-displayX)*tileSize, (alien.getCoordinate().y-displayY)*tileSize, alien.getDimension().width*tileSize, alien.getDimension().height*tileSize);
    }
}
