package View.Squares;

import View.BoardPanel;

import java.awt.*;

public class RocketView implements SquareView {

    BoardPanel boardPanel;

    public RocketView(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }

    @Override
    public void draw(Graphics2D g, int x, int y){
        g.setColor(Color.GRAY);
        g.fillRect(x, y, boardPanel.getTileSize(), boardPanel.getTileSize());

    }
}
