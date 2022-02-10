package Model;

import Model.Layer0.Parcel;

import static Model.GameConstants.BOARD_SIZE;

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
        this.terrain = new Parcel[GameConstants.BOARD_SIZE][BOARD_SIZE];
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
        this.terrain = (new RandomLandGeneration()).getBoard();
    }

    //TODO : Algo Thomas avec des jolis commentaire :)
}
