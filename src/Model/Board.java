package Model;

import Model.Entities.Entity;

public class Board {

    private Entity[][] board;

    public Board() {
        this.board = new Entity[GameConstants.BOARD_COLS][GameConstants.BOARD_ROWS];
    }

    public Entity[][] getBoard() {
        return board;
    }
}
