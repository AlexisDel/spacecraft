package View.Tiles;

import View.BoardPanel;

import java.awt.*;

public class LandView implements TileView {

    BoardPanel boardPanel;

    public LandView(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        g.setColor(Color.ORANGE);
        g.fillRect(x*boardPanel.getTileSize(), y*boardPanel.getTileSize(), boardPanel.getTileSize(), boardPanel.getTileSize());
    }
}
