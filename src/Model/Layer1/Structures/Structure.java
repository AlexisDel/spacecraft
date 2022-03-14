package Model.Layer1.Structures;

import Model.Layer1.Entities.Entity;
import Model.Layer1.InteractiveItem;

import java.awt.*;
import java.util.ArrayList;

/**
 * Classe décrivant les structure, notamment les bâtiments
 */

public abstract class Structure extends InteractiveItem {
    private int capacity;
    /**Points occupés par le batiment*/
    private ArrayList<Entity> occupants;

    /**
     * Constructeur
     * @param coordinate coordonnées globales de la structure
     * @param dimension width et size de la structure
     * @param healthPoints points de vie de la structure
     * @param capacity nombre max d'occupants
     */
    public Structure(String name,Point coordinate, Dimension dimension, int healthPoints, int capacity){
        super(name, coordinate, dimension, healthPoints);

        this.capacity=capacity;
        this.occupants= new ArrayList<Entity>();
    }

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
        if(this.occupants.size()<4 && this.occupants.size()!=0)
        this.occupants.add(e);
    }
    /** Enlève un occupant de la liste d'occupants de la structure*/
    public void removeOccupant(Entity e){
        this.occupants.remove(e);
    }
}
