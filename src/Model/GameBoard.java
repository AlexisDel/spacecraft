package Model;

import Model.Squares.Containers.Building;
import Model.Squares.Containers.Land;

/**
 * Classe représentant notre plateau de jeu
 */
public class GameBoard {

    GameSquare[][] board;

    /**
     * Création et initialisation du plateau de jeu
     */
    public GameBoard() {
        this.board = new GameSquare[GameConstants.BOARD_WIDTH][GameConstants.BOARD_HEIGHT];
        this.initBoard();
        changeCellType(new Building(), 1, 1);
        changeCellType(new Building(), 3, 5);
        changeCellType(new Building(), 3, 0);

        printBoardConsole();
    }

    /**
     * Fonction permettant d'initialiser le plateau du jeu
     */
    private void initBoard(){
        // Pour chaque case du plateau
        for(int i = 0; i < GameConstants.BOARD_WIDTH; i++){
            for(int j = 0; j < GameConstants.BOARD_HEIGHT; j++){
                this.board[i][j] = new Land();
            }
        }
    }

    /**
     * Fonction permettant de changer le type d'une case
     * @param gameSquare le nouveau type de la case
     * @param x abscisse
     * @param y ordonnée
     */
    public void changeCellType(GameSquare gameSquare, int x, int y){
        this.board[x][y] = gameSquare;
    }

    public void printBoardConsole(){
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
}
