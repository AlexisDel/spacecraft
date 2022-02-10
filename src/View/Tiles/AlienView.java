package View.Tiles;

import Model.Layer1.Entities.Alien;

import java.awt.*;

public class AlienView extends ObjectView{

    public AlienView(Alien alien) {
        super(alien);
    }

    @Override
    public void draw(Graphics2D g, int tileSize, int displayX, int displayY) {
        g.setColor(Color.GREEN);
        g.fillRect((object.getCoordinate().x-displayX)*tileSize, (object.getCoordinate().y-displayY)*tileSize, object.getDimension().width*tileSize, object.getDimension().height*tileSize);
    }
}
