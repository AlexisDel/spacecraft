package Model;

import Model.Layer0.Parcel;
import Model.Layer1.Entities.Alien;
import Model.Layer1.Entities.Entity;
import Model.Layer1.Structures.Spacecraft;
import Model.Layer1.Structures.Structure;

import java.awt.*;
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

        //TODO : test
        structures.add(new Spacecraft(new Point(0,0), new Dimension(4, 4), 1000, 10));
        structures.add(new Spacecraft(new Point(4,4), new Dimension(4, 4), 1000, 10));
        entities.add(new Alien(new Point(0,5), new Dimension(1,1), 150, 10));
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
