package Model.Layer1.Structures;

import Model.Layer1.Entities.Entity;
import Model.Layer1.Layer1Object;
import Model.Object;

import java.awt.*;
import java.util.ArrayList;

/**
 * Classe décrivant les structure, notamment les bâtiments
 */

public abstract class Structure extends Layer1Object {
    private int capacity;
    /**Points occupés par le batiment
     * TODO tell Alexis about this for view change*/
    private ArrayList<Point> pointsOccupied;
    private ArrayList<Entity> occupants;

    /**
     * Constructeur
     * @param coordinate coordonnées globales de la structure
     * @param dimension width et size de la structure
     * @param healthPoints points de vie de la structure
     * @param capacity nombre max d'occupants
     */
    public Structure(String name,Point coordinate, Dimension dimension, int healthPoints, int capacity, String imagePath){
        super(name, coordinate, dimension,healthPoints,imagePath);
        //ajout des 4 points qui par defaut composent
        //TODO turn this to a methode fix the +1
        this.pointsOccupied= new ArrayList<>();
        this.pointsOccupied.add(coordinate);
        this.pointsOccupied.add(new Point(coordinate.x+1,coordinate.y+1));
        this.pointsOccupied.add(new Point(coordinate.x,coordinate.y+1));
        this.pointsOccupied.add(new Point(coordinate.x+1,coordinate.y));

        this.capacity=capacity;
        this.occupants= new ArrayList<Entity>();
    }

    /** getter de la capacité de la structure */
    public int getCapacity() {
        return capacity;
    }
    /** ArrayList manipulation */
    /** getter de la liste de points occupés par la structure*/
    public ArrayList<Point> getPointsOccupied() {
        return pointsOccupied;
    }

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
