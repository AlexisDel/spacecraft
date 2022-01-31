package View;

import Model.Board;
import Model.Entities.Character;
import Model.Entities.Entity;

import javax.swing.*;
import java.awt.*;

public class BoardView extends JPanel {

    private Entity[][] board;

    public BoardView(Board board) {
        this.board = board.getArray();
    }

    @Override
    public void paint(Graphics g) {
        for (int i = 0; i < board.length; ++i) {
            for(int j = 0; j < board[i].length; ++j) {
                if(board[i][j] instanceof Character){
                    g.drawOval(j*4, i*4, 5, 5);
                }
            }
        }
    }
}
