package Model;

import Model.Cells.Containers.Building;
import Model.Cells.Containers.Land;

public class GameBoard {

    GameCell[][] board;

    public GameBoard() {
        this.board = new GameCell[GameConstants.BOARD_WIDTH][GameConstants.BOARD_HEIGHT];
        changeCellType(new Building(), 1, 1);
        changeCellType(new Building(), 3, 5);
        changeCellType(new Building(), 3, 0);

        // Test
        for(int i = 0; i < GameConstants.BOARD_WIDTH; i++){
            for(int j = 0; j < GameConstants.BOARD_HEIGHT; j++){
                if(this.board[i][j] instanceof Building){
                    System.out.print("X");
                }
                else {
                    System.out.print("O");
                }
            }
            System.out.println();
        }

    }

    private void initBoard(){
        for(int i = 0; i < GameConstants.BOARD_WIDTH; i++){
            for(int j = 0; j < GameConstants.BOARD_HEIGHT; j++){
                this.board[i][j] = new Land();
            }
        }
    }

    public void changeCellType(GameCell gameCell, int x, int y){
        this.board[x][y] = gameCell;
    }
}
