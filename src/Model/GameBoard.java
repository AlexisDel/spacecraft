package Model;

import Model.Layer0.Land;
import Model.Layer0.Parcel;

import static Model.GameConstants.*;

/**
 * Classe représentant le terrain du jeu
 */
public class GameBoard {

    /** Tableau représentant le terrain de jeu */
    Parcel[][] board;

    /**
     * Constructeur
     */
    public GameBoard() {
        this.board = new Parcel[BOARD_WIDTH][BOARD_HEIGHT];
        initBoard();
    }

    /**
     * Initialise le terrain du jeu avec des parcel de terre et des montagnes
     */
    public void initBoard(){
        // Pour chaque parcelle du terrain
        for(int i = 0; i < BOARD_WIDTH; i++){
            for(int j = 0; j < BOARD_HEIGHT; j++){
                this.board[i][j] = new Land(true);
            }
        }
    }

    //TODO : Algo Thomas avec des jolis commentaire :)
}
