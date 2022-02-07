package View.Tiles;

import java.awt.*;

public class MountainView implements TileView{

    @Override
    public void draw(Graphics2D g, int x, int y, int tileSize) {
        g.setColor(Color.GRAY);
        g.fillRect(x*tileSize, y*tileSize, tileSize, tileSize);
    }
}
