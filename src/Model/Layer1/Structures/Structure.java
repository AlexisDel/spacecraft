package Model.Layer1.Structures;

import Model.Layer1.Entities.Entity;
import Model.Layer1.Object;

import java.awt.*;
import java.util.ArrayList;

/**
 * Classe décrivant les structure, notamment les bâtiments
 */

public abstract class Structure extends Object {
    private int capacity;
    private ArrayList<Entity> occupants;

    /**
     * Constructeur
     * @param coordinate coordonnées globales de la structure
     * @param dimension width et size de la structure
     * @param healthPoints points de vie de la structure
     * @param capacity nombre max d'occupants
     */
    public Structure(Point coordinate, Dimension dimension, int healthPoints, int capacity){
        super(coordinate, dimension, healthPoints);
        this.capacity=capacity;
        this.occupants= new ArrayList<Entity>();
    }
    /** getters*/

    /** getter de la capacité de la structure */
    public int getCapacity() {
        return capacity;
    }
    /** ArrayList manipulation */
    /** getter de la liste d'occupants de la structure*/
    public ArrayList<Entity> getOccupants() {
        return occupants;
    }
    /** Ajoute un occupant a la liste des occupants*/
    public void addOccupant(Entity e){
        if(this.occupants.size()<4)
        this.occupants.add(e);
    }
    /** Enlève un occupant de la liste d'occupants de la structure*/
    public void removeOccupant(Entity e){
        this.occupants.remove(e);
    }
}
