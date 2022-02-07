package View.Tiles;

import Model.Layer1.Structures.Spacecraft;

import java.awt.*;

public class SpacecraftView implements ObjectView{

    Spacecraft spacecraft;

    public SpacecraftView(Spacecraft spacecraft) {
        this.spacecraft = spacecraft;
    }

    @Override
    public void draw(Graphics2D g, int tileSize, int displayX, int displayY) {
        g.setColor(Color.BLUE);
        g.fillRect((spacecraft.getCoordinate().x-displayX)*tileSize, (spacecraft.getCoordinate().y-displayY)*tileSize, spacecraft.getDimension().width*tileSize, spacecraft.getDimension().height*tileSize);

    }
}
