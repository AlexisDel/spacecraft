package Model;

public class Board {

    private Entity[][] board;

    public Board() {

        this.board = new Entity[GameConstants.BOARD_WIDTH][GameConstants.BOARD_HEIGHT];
    }

    public Entity[][] getBoard() {
        return board;
    }
}
