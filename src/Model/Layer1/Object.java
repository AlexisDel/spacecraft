package Model.Layer1;

import java.awt.*;

/**
* Classe décrivant les objets de Layer1 et Layer2: objets avec des healthPoints, des coordonnées
 * et des dimensions
 */
public abstract class Object {
    private int healthPoints;
    private Point coordinate;
    private Dimension dimension;

    /**
     * Constructeur
     * @param coordinate coordonnées globales de l'objet
     * @param dimension width et size du objet
     * @param healthPoints points de vie de l'objet
     */
    public Object(Point coordinate, Dimension dimension, int healthPoints){
        this.healthPoints=healthPoints;
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

