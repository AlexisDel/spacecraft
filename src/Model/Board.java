package Model;

public class Board {

    private Entity[][] board;

    public Board() {
        this.board = new Entity[gameConstants.BOARD_WIDTH][gameConstants.BOARD_HEIGHT];
    }

    public Entity[][] getBoard() {
        return board;
    }
}
