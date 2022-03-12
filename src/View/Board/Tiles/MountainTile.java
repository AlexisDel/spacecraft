package View.Board.Tiles;

import Model.Layer0.Mountain;

import java.awt.*;

import static View.ViewConstants.TILE_SIZE;

public class MountainTile extends ItemTile {

    public MountainTile(Mountain mountain) {
        super(mountain);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.GRAY);
        g.fillRect((item.getCoordinate().x)*TILE_SIZE, (item.getCoordinate().y)*TILE_SIZE, item.getDimension().width*TILE_SIZE, item.getDimension().height*TILE_SIZE);
    }
}
