package View.Squares;

import View.BoardPanel;

import java.awt.*;

public class LandView implements SquareView {

    BoardPanel boardPanel;

    public LandView(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, boardPanel.getBoardTileSize(), boardPanel.getBoardTileSize());
    }
}
