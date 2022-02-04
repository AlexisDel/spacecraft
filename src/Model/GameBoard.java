package Model;

import Model.Entities.Character.Alien;
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
        this.board = (new RandomLandGeneration()).getBoard();
        //this.initBoard();
        getSquare(1,5).addEntityToSquare(new Alien(), 0,0);
        changeCellType(new Building(), 1, 1);
        changeCellType(new Building(), 3, 5);
        changeCellType(new Building(), 3, 12);
        changeCellType(new Building(), 9, 9);
        changeCellType(new Building(), 9, 32);
        changeCellType(new Building(), 12, 9);
        changeCellType(new Building(), 10, 10);
        getSquare(0,0).addEntityToSquare(new Alien(), 3, 3);
        getSquare(12, 12).addEntityToSquare(new Alien(), 0, 0);
    }

    /**
     * Fonction permettant d'initialiser le plateau du jeu
     */
    public void initBoard(){
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

    public GameSquare getSquare(int x, int y) {
        return board[x][y];
    }
}
