package View.Tiles;

import Model.Layer0.Mountain;

import java.awt.*;

public class MountainView extends ObjectView{

    public MountainView(Mountain mountain) {
        super(mountain);
    }

    @Override
    public void draw(Graphics2D g, int tileSize, int displayX, int displayY) {
        g.setColor(Color.GRAY);
        g.fillRect((object.getCoordinate().x-displayX)*tileSize, (object.getCoordinate().y-displayY)*tileSize, object.getDimension().width*tileSize, object.getDimension().height*tileSize);

    }
}
