package View.Board.Tiles;

import Model.Layer1.Entities.SpaceMarine;

import java.awt.*;

public class SpaceMarineTile extends ItemTile {

    public SpaceMarineTile(SpaceMarine spaceMarine) {
        super(spaceMarine);
    }

    @Override
    public void draw(Graphics2D g, int tileSize, int displayX, int displayY) {
        g.setColor(Color.CYAN);
        g.fillRect((item.getCoordinate().x-displayX)*tileSize, (item.getCoordinate().y-displayY)*tileSize, item.getDimension().width*tileSize, item.getDimension().height*tileSize);
    }
}