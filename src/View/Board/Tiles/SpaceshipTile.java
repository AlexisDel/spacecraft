package View.Board.Tiles;

import Model.Layer1.Structures.Spaceship;

import java.awt.*;

public class SpaceshipTile extends ItemTile {

    public SpaceshipTile(Spaceship spaceship) {
        super(spaceship);
    }

    @Override
    public void draw(Graphics2D g, int tileSize, int displayX, int displayY) {
        g.setColor(Color.BLUE);
        g.fillRect((item.getCoordinate().x-displayX)*tileSize, (item.getCoordinate().y-displayY)*tileSize, item.getDimension().width*tileSize, item.getDimension().height*tileSize);

    }
}
