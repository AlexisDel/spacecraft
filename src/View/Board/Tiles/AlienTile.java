package View.Board.Tiles;

import Model.Layer1.Entities.Alien;

import java.awt.*;

import static View.ViewConstants.TILE_SIZE;

public class AlienTile extends ItemTile {

    public AlienTile(Alien alien) {
        super(alien);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.GREEN);
        g.fillRect((item.getCoordinate().x)*TILE_SIZE, (item.getCoordinate().y)*TILE_SIZE, item.getDimension().width*TILE_SIZE, item.getDimension().height*TILE_SIZE);
    }
}
