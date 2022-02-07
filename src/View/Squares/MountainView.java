package View.Squares;

import View.BoardPanel;

import java.awt.*;

public class MountainView implements SquareView{

    BoardPanel boardPanel;

    public MountainView(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        g.setColor(Color.GRAY);
        g.fillRect(x, y, boardPanel.getBoardTileSize(), boardPanel.getBoardTileSize());
    }
}
