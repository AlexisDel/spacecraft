package View;

import Model.Board;
import Model.Entities.Character;
import Model.Entities.Entity;
import Model.GameConstants;

import javax.swing.*;
import java.awt.*;

public class BoardView extends JPanel {

    private Entity[][] board;
    private final static int GAP = 2;

    public BoardView(Board board) {
        this.board = board.getArray();

        setPreferredSize(new Dimension(600,600));
        setLayout(new BorderLayout(GAP, GAP));
        setBorder(BorderFactory.createLineBorder(Color.PINK, GAP));
        GridLayout layout = new GridLayout(GameConstants.BOARD_ROWS, GameConstants.BOARD_COLS);
        setLayout(layout);

        for (int i = 0; i < GameConstants.BOARD_ROWS; i++) {

            for (int j = 0; j < GameConstants.BOARD_COLS; j++) {
                add(new Tile());
            }
        }
    }


    @Override
    public void paint(Graphics g) {
        System.out.println("painting");
        super.paint(g);

        for(Entity[] t: this.board){
            for(Entity e: t ){
                if(e instanceof Character) {
                    System.out.println("iter");
                    g.drawString("Character", e.getCoordinate().x*GameConstants.BOARD_SQUARESIZE, e.getCoordinate().y*GameConstants.BOARD_SQUARESIZE);
                }
            }
        }
        System.out.println("done painting");
    }

    class Tile extends JLabel {

        Tile() {
            setPreferredSize(new Dimension(GameConstants.BOARD_SQUARESIZE, GameConstants.BOARD_SQUARESIZE));
            setBorder(BorderFactory.createLineBorder(Color.BLACK, GAP));
        }
    }

}
