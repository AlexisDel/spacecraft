package Model;

import Model.Layer0.Mountain;
import Model.Layer1.Entities.Alien;
import Model.Layer1.Entities.Entity;
import Model.Layer1.Structures.Spacecraft;
import Model.Layer1.Structures.Structure;

import java.awt.*;
import java.util.ArrayList;

public class GameBoard {

    /** Le terrain du jeu */
    ArrayList<Mountain> mountains;
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
        mountains = new RandomLandGeneration().getBoard();
        structures = new ArrayList<>();
        entities = new ArrayList<>();

        //TODO : test
        structures.add(new Spacecraft(new Point(0,0), new Dimension(4, 4), 1000, 10));
        structures.add(new Spacecraft(new Point(4,4), new Dimension(4, 4), 1000, 10));
        entities.add(new Alien(new Point(0,5), 150, 10));
    }

    /**
     * @return La liste des montagnes présentes sur le terrain
     */
    public ArrayList<Mountain> getMountains() {
        return mountains;
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
