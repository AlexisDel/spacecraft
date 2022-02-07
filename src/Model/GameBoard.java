package Model;

import Model.Layer0.Parcel;
import Model.Layer1.Structure;
import Model.Layer2.Entity;

import java.util.ArrayList;

public class GameBoard {

    /** Le terrain du jeu */
    GameTerrain gameTerrain;
    /** Liste représentant les structures qui sont sur le terrain de jeu
     *  une structure étant un objet immobile */
    ArrayList<Structure> structures;
    /** Liste représentant les entités qui sont sur le terrain de jeu
     *  une entité étant un objet que l'on peut déplacer sur la carte */
    ArrayList<Entity> entities;

    /**
     * Constructeur, initialise les différentes couches qui composent notre terrain de jeu
     */
    public GameBoard() {
        gameTerrain = new GameTerrain();
        structures = new ArrayList<>();
        entities = new ArrayList<>();
    }

    /**
     * @return Le tableau 2D représentant le terrain de jeu
     */
    public Parcel[][] getTerrain() {
        return gameTerrain.getTerrainFromGameTerrain();
    }

    /**
     * @return La liste des structures présentes sur le terrain
     */
    public ArrayList<Structure> getStructures() {
        return structures;
    }

    /**
     * @return La liste des entités présentes sur le terrain
     */
    public ArrayList<Entity> getEntities() {
        return entities;
    }
}
