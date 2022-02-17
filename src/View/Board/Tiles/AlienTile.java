package View.Board.Tiles;

import Model.Layer1.Entities.Alien;

import java.awt.*;

public class AlienTile extends ItemTile {

    public AlienTile(Alien alien) {
        super(alien);
    }

    @Override
    public void draw(Graphics2D g, int tileSize, int displayX, int displayY) {
        g.setColor(Color.GREEN);
        g.fillRect((item.getCoordinate().x-displayX)*tileSize, (item.getCoordinate().y-displayY)*tileSize, item.getDimension().width*tileSize, item.getDimension().height*tileSize);
    }
}
