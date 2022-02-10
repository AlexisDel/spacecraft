package View.Tiles;

import Model.Layer1.Structures.Spacecraft;

import java.awt.*;

public class SpacecraftView extends ObjectView{

    public SpacecraftView(Spacecraft spacecraft) {
        super(spacecraft);
    }

    @Override
    public void draw(Graphics2D g, int tileSize, int displayX, int displayY) {
        g.setColor(Color.BLUE);
        g.fillRect((object.getCoordinate().x-displayX)*tileSize, (object.getCoordinate().y-displayY)*tileSize, object.getDimension().width*tileSize, object.getDimension().height*tileSize);

    }
}
