package View.Tiles;

import View.BoardPanel;

import java.awt.*;

public class MountainView implements TileView{
    BoardPanel boardPanel;

    public MountainView(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        g.setColor(Color.GRAY);
        g.fillRect(x*boardPanel.getTileSize(), y*boardPanel.getTileSize(), boardPanel.getTileSize(), boardPanel.getTileSize());
    }
}
