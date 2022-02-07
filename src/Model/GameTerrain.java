package Model;

import Model.Layer0.Land;
import Model.Layer0.Parcel;

import static Model.GameConstants.BOARD_HEIGHT;
import static Model.GameConstants.BOARD_WIDTH;

/**
 * Classe représentant le terrain du jeu
 */
public class GameTerrain {

    /** Tableau représentant le terrain de jeu */
    private Parcel[][] terrain;

    /**
     * Constructeur, créer et initialise le terrain de jeu
     */
    public GameTerrain() {
        this.terrain = new Parcel[BOARD_WIDTH][BOARD_HEIGHT];
        initTerrain();
    }

    /**
     * @return le tableau 2D représentant le terrain de jeu
     */
    public Parcel[][] getTerrainFromGameTerrain() {
        return terrain;
    }

    /**
     * Initialise le terrain du jeu avec des parcel de terre et des montagnes
     */
    public void initTerrain(){
        // Pour chaque parcelle du terrain
        for(int i = 0; i < BOARD_WIDTH; i++){
            for(int j = 0; j < BOARD_HEIGHT; j++){
                this.terrain[i][j] = new Land();
            }
        }
    }

    //TODO : Algo Thomas avec des jolis commentaire :)
}
