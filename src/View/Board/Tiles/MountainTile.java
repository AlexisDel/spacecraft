package View.Board.Tiles;

import Model.Layer0.Mountain;

import java.awt.*;

public class MountainTile extends ItemTile {

    public MountainTile(Mountain mountain) {
        super(mountain);
    }

    @Override
    public void draw(Graphics2D g, int tileSize, int displayX, int displayY) {
        g.setColor(Color.GRAY);
        g.fillRect((item.getCoordinate().x-displayX)*tileSize, (item.getCoordinate().y-displayY)*tileSize, item.getDimension().width*tileSize, item.getDimension().height*tileSize);

    }
}
