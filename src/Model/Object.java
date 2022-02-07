package Model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Cette classe décrit les objets de Layer1 et Layer2: objets avec des HealthPoints, des coordonnées
 * et des dimensions
 */
public abstract class Object {
    private int healthPoints;
    private Point coordinate;
    private Dimension dimension;

    /**
     * Constructor
     * @param coordinate coordonnees globales du objet
     * @param dimension width et size du objet
     * @param healthpoints points de vie du objet
     */
    public Object(Point coordinate, Dimension dimension, int healthpoints){
        this.healthPoints=healthpoints;
        this.coordinate=coordinate;
        this.dimension=dimension;
    }
    /** getters*/
    public int getHealthPoints() {
        return healthPoints;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public Point getCoordinate() {
        return coordinate;
    }
}
