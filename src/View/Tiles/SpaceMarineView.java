package View.Tiles;

import Model.Layer1.Entities.Alien;
import Model.Layer1.Entities.SpaceMarine;

import java.awt.*;

public class SpaceMarineView extends ObjectView{

    public SpaceMarineView(SpaceMarine spaceMarine) {
        super(spaceMarine);
    }

    @Override
    public void draw(Graphics2D g, int tileSize, int displayX, int displayY) {
        g.setColor(Color.CYAN);
        g.fillRect((object.getCoordinate().x-displayX)*tileSize, (object.getCoordinate().y-displayY)*tileSize, object.getDimension().width*tileSize, object.getDimension().height*tileSize);
    }
}