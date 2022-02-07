package Model.Layer1;

import Model.Layer2.Entity;
import Model.Object;

import java.awt.*;
import java.util.ArrayList;

public abstract class Structure extends Object {
    private int capacity;
    private ArrayList<Entity> occupants;

    /**
     * Constructor
     * @param coordinate coordonnees globales de la structure
     * @param dimension width et size de la structure
     * @param healthpoints points de vie de la structure
     * @param capacity nombre max d'occupants
     */
    public Structure(Point coordinate, Dimension dimension, int healthpoints, int capacity){
        super(coordinate, dimension,healthpoints);
        this.capacity=capacity;
        this.occupants= new ArrayList<Entity>();
    }
    /** getters*/
    public int getCapacity() {
        return capacity;
    }
    /** ArrayList manipulation */
    public ArrayList<Entity> getOccupants() {
        return occupants;
    }

    public void addOccupant(Entity e){
        if(this.occupants.size()<4)
        this.occupants.add(e);
    }

    public void removeOccupant(Entity e){
        this.occupants.remove(e);
    }
}
