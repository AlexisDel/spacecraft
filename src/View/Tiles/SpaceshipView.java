package View.Tiles;

import Model.Layer1.Structures.Spaceship;

import java.awt.*;

public class SpaceshipView extends ObjectView{

    public SpaceshipView(Spaceship spaceship) {
        super(spaceship);
    }

    @Override
    public void draw(Graphics2D g, int tileSize, int displayX, int displayY) {
        g.setColor(Color.BLUE);
        g.fillRect((object.getCoordinate().x-displayX)*tileSize, (object.getCoordinate().y-displayY)*tileSize, object.getDimension().width*tileSize, object.getDimension().height*tileSize);

    }
}
