package View.Board.Tiles;


import Model.Layer1.Structures.Meteorite;

import java.awt.*;

public class MeteoriteTile extends ItemTile {

    public MeteoriteTile(Meteorite meteorite) {
        super(meteorite);
    }

    @Override
    public void draw(Graphics2D g, int tileSize, int displayX, int displayY) {
        g.setColor( new Color(139,69,19));
        g.fillRect((item.getCoordinate().x-displayX)*tileSize, (item.getCoordinate().y-displayY)*tileSize, item.getDimension().width*tileSize, item.getDimension().height*tileSize);
    }
}
