package Model.Layer1.Entities;

import Model.GameBoard;
import Model.Layer1.InteractiveItem;

import java.awt.*;
import java.util.ArrayList;

/**
 * Cette classe regroupe les entités du jeu,
 * à savoir les objets qui peuvent être déplacé
 */
public abstract class Entity extends InteractiveItem {
    /**Attributes*/
    private int speed;
    private ArrayList<Action>actions;
    private GameBoard gameBoard;
    /**
     * Constructeur
     * @param coordinate   coordonnées globales de l'entité
     * @param dimension    width et size de l'entité
     * @param healthPoints points de vie de l'entité
     * @param speed vitesse en ms de déplacement de l'entité
     */

    // TODO fix le gameBoard
    public Entity(String name,Point coordinate, Dimension dimension, int healthPoints, int speed, GameBoard gameBoard) {
        super(name, coordinate, dimension, healthPoints);
        this.speed = speed;
        this.actions= new ArrayList<>();
        this.gameBoard = gameBoard;
    }

    /** Cette méthode déplace l'entité */
    public void move(Point p){
        super.setCoordinate(p);
    }

    /**Ajoute une action a la liste d'actions de l'entité*/
    public void addAction(Action a){
        this.actions.add(a);
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    /**
     * getter de GameBoard
     * @return
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }
}
