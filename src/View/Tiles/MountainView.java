package View.Tiles;

import Model.Layer0.Mountain;

import java.awt.*;

public class MountainView implements ObjectView{

    Mountain mountain;

    public MountainView(Mountain mountain) {
        this.mountain = mountain;
    }

    @Override
    public void draw(Graphics2D g, int tileSize, int displayX, int displayY) {
        g.setColor(Color.GRAY);
        g.fillRect((mountain.getCoordinate().x-displayX)*tileSize, (mountain.getCoordinate().y-displayY)*tileSize, mountain.getDimension().width*tileSize, mountain.getDimension().height*tileSize);

    }
}
